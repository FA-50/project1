package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s03.Address;
import model.s03.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceContext1Test {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-exp-6");

    @BeforeEach
    void setUp(){

    }
    @Test
    void tearDown(){
        emf.close();
    }
    @Test
    void first_cache(){



        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Member foundedMember = makeMember();

        try {
            System.out.println("첫번째 조회 시도");
            Member firstFoundMember = entityManager.find(Member.class, foundedMember.getId());
            System.out.println(firstFoundMember.getName());

            System.out.println("두번째 조회 시도");
            Member secondFoundMember = entityManager.find(Member.class, foundedMember.getId());
            System.out.println(firstFoundMember.getName());

            System.out.println("세번째 조회 시도");
            Member thirdFoundMember = entityManager.find(Member.class, foundedMember.getId());
            System.out.println(firstFoundMember.getName());

        }catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            throw new RuntimeException("에러 발생",e);
        }finally {
            entityManager.close();
        }

        // EntityManager은 DB 격리수준과 별개로 기본적으로 Repeatable Read를 제공
        // 따로 격리수준을 부여 못하남?
    }

    private Member makeMember() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Member member;

        try{
            transaction.begin();

            String name = "이정수";
            String password = "wjd747";
            String email = "wjdtn747@naver.com";

            member =  new Member(
                    name,
                    password,
                    email,
                    new Address(
                            "2",
                            "3",
                            "4"
                    )
            );

            entityManager.persist(member);

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            throw new RuntimeException("생성 에러 발생",e);
        }finally {
            entityManager.close();
        }

        return member;
    }
}