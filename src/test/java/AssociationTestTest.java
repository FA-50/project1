import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s03.Address;
import model.s03.Member;
import model.s03.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssociationTestTest {

    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("hibernate-exp-6");

    EntityManager em;
    Member member;
    Order order;

    Long memberId;

    @BeforeEach
    void setUp(){
       em = emf.createEntityManager();
       if(member == null){
           member = new Member(
                   "노시한",
                   "wjd747",
                   "wjdtn747@naver.com",
                   new Address(
                           "2",
                           "3",
                           "4"
                   )
           );
       }
    }

    @AfterEach
    void tearDown(){
        em.close();
    }

    @Test
    void 다대일_테스트(){

        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();

            em.persist(member);

            memberId = member.getId();
            transaction.commit();
        } catch(Exception e){
            // 런타임 Exception 쓰기보다는
            // 구체화된 커스텀 Exception을 생성하여 활용하는게 좋다
            new RuntimeException("테스트에 실패했습니다", e);
            // Exception 발생 시 Exception 원본 객체를 매개변수에 전달하는게 좋다.
            transaction.rollback();

            // e.printStackTrace() : 예외 발생 로그를
            // Stack 자료구조에 쌓아놓은 것
        }
    }

    @Test
    void 다대일_테스트2(){

        // em 은 다른 객체이므로
        // 그래프 탐사
        order = new Order(
            em.find(Member.class,memberId)
        );

        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();

            em.persist(order);

            transaction.commit();
        } catch(Exception e){
            // 런타임 Exception 쓰기보다는
            // 구체화된 커스텀 Exception을 생성하여 활용하는게 좋다
            new RuntimeException("테스트에 실패했습니다", e);
            // Exception 발생 시 Exception 원본 객체를 매개변수에 전달하는게 좋다.
            transaction.rollback();

            // e.printStackTrace() : 예외 발생 로그를
            // Stack 자료구조에 쌓아놓은 것
        }
    }

    @Test
    void 다대일_테스트3(){

        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();

            Order foundedOrder = em.find(Order.class, order.getId());



            transaction.commit();
        } catch(Exception e){
            // 런타임 Exception 쓰기보다는
            // 구체화된 커스텀 Exception을 생성하여 활용하는게 좋다
            new RuntimeException("테스트에 실패했습니다", e);
            // Exception 발생 시 Exception 원본 객체를 매개변수에 전달하는게 좋다.
            transaction.rollback();

            // e.printStackTrace() : 예외 발생 로그를
            // Stack 자료구조에 쌓아놓은 것
        }
    }

}