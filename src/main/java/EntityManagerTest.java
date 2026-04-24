


// EntityManager 는 내부에 영속성컨텍스트가 존재
// JPA에 정의된 인터페이스 -> Hibernate는 EntityManager를 구현
// EntityManager는 ORM 동작 전체를 관리하는 역할을 수행
// 단순히 DAO 기능을 통한 CRUD를 수행할 뿐 아니라, 영속성컨텍스트를 통한
// Entity의 생애주기를 관리 -> 트랜잭션과 생애주기를 함께 수행

// EntityManagerFactory 내부에 Session Factory가 존재하고, Connection Pool이 존재
// EntityManagerFactory는 어플리케이션에서 오직 하나만 존재 -> Connection Pool을 포함하므로

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s02.Post;

public class EntityManagerTest {
	// EntiyManagerFactory 생성
	private static final EntityManagerFactory emf;
	static{
		// xml 파일 정보를 통해 초기화
		emf = Persistence.createEntityManagerFactory("hibernate-exp-4");
	}
	public static void main(String[] args) {
		// EntityManager 생성
		EntityManager entityManager = emf.createEntityManager();
		// JPA에서는 하나의 작업단위 당 하나의 EntityManager 객체를 생성하여 활용
		// Transaction 자바객체 생성
		EntityTransaction transaction = entityManager.getTransaction();
		try{
			// 트랜잭션 시작
			transaction.begin();
			// Transient : 비영속
			Post post = new Post(1, "title", "contents");
			// Managed : 영속
			entityManager.persist(post);
			// Removed : 삭제
			entityManager.remove(post);
			// 트랜잭션 종료
			transaction.commit();
		}catch (Exception e){
			// 트랜잭션 실패 시 rollback.
			transaction.rollback();
		} finally{
			entityManager.close();
			// EntityManager는 기능 하나당 EntityManager 객체를 사용하므로,
			// 기능 수행이 모두 끝난 경우 객체를 종료
		}
	}
}
