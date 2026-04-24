package model;

import jakarta.persistence.*;
import model.s03.*;
import model.s03.Order;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JpqlTestsTest {

    private static EntityManagerFactory emf;

    Member m1;
    Product p1;
    Order o1;
    OrderItem oi1;


    @BeforeAll
    static void setUpStatic(){
        emf = Persistence.createEntityManagerFactory("hibernate-exp-6");
    }

    @AfterAll
    static void tearDownStatic(){
        emf.close();
    }

    @BeforeEach
    void setUp(){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            String password = "wjd747";



            for(int i = 1 ; i < 100 ; i++){
                m1 = new Member(
                        "정수"+i,
                        password,
                        "wjdtn%d@naver.com".formatted(i),
                        new Address(
                                "2",
                                "3",
                                "4"
                        )
                );
                p1 = new Product("나무배트" + i, 150000, i);

                o1 = new Order(m1);
                oi1 = new OrderItem(p1,i);
                o1.addOrderItem(oi1);
                entityManager.persist(m1);
            }

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            throw new RuntimeException("생성 에러 발생",e);
        }finally {
            entityManager.close();
        }
    }


    @Test
    void JPQL_테스트하깅(){
        EntityManager em = emf.createEntityManager();
        /*
        SELECT
            m.id,
            m.name,
            m.password,
            m.email,
            m.balance
        FROM Member m 과 동일
         */
        // DB Table의 필드가 아닌, Entity 객체에 존재하는 필드를 조회
        String jpql = """
                SELECT m 
                    FROM Member m
                """;
        // Class Type을 클래스리터럴로 설정해서 쿼리 클래스 생성 시 해당 Class Type의 값으로 반환받을 수 있다.
        Query query = em.createQuery(jpql, Member.class);
        List<Member> resultList = query.getResultList();

        resultList.forEach(s-> System.out.println(s.getName()));

    }

    @Test
    void JPQL_조건절_검사(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            String JPQLSQLTEXT = """
                    SELECT m
                    FROM Member m
                    WHERE m.email = 'wjdtn50@naver.com'
                    """;

            List<Member> resultList = em.createQuery(JPQLSQLTEXT, Member.class).getResultList();

            System.out.println();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }


    // @Deprecated : 더이상 사용하지 않는 클래스에 선언하는 어노테이션
    // 어노테이션 Retention : 어노테이션의 생명주기

    @Test
    void JPQL_매개변수(){
        // Begin
        EntityManager em = emf.createEntityManager();

        // When
        String jpql = """
                SELECT m
                FROM Member m
                WHERE m.email = :email
                """;
        // JPA에서는 SQL 내 변수 선언 시 :email로 설정

        // Then
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        query.setParameter("email","wjdtn50@naver.com");

        Member singleResult = query.getSingleResult();
        System.out.println(singleResult.getName());
    }

}