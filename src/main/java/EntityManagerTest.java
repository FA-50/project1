


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
	private static final EntityManagerFactory emf;

	static{
		// xml 파일 정보를 통해 초기화
		emf = Persistence.createEntityManagerFactory("hibernate-exp-4");
	}

	public static void main(String[] args) {
		// EntityManager 생성
		// Hibernate의 세션을 감싸는 객체
		
		
		// Thread Safe하지 않으므로, 하나의 스레드는 하나의 Entity Manager를 사용
		// SqlSession처럼 Connection 하나만 사용하므로
		// 톰캣이 요청 하나 당 스레드 하나 씩 생성하므로 신경안써도되나 ,
		// Webflux를 사용하는 경우 신경을 써야함.

		// 하나의 EntityManger는 Persistence Context(First Level Cache + Action Queue + Snapshot)를 포함
		EntityManager entityManager = emf.createEntityManager();

		// JPA에서는 하나의 작업단위 당 하나의 EntityManager 객체를 생성하여 활용

		// Transaction 자바객체 생성
		EntityTransaction transaction = entityManager.getTransaction();

		try{
			// Transaction BEGIN 실행
			transaction.begin();

			// 비영속 상태
			Post post = new Post(1, "title", "contents");

			// 영속 상태
			// INSERT 수행됨
			// Dirty Checking을 통해 객체 변경을 감지 후
			// 해당 변경에 맞게 INSERT 쿼리를 작성하여 DB로 전송
			entityManager.persist(post);
			
			// Transaction Commit
			transaction.commit();

		}catch (Exception e){
			System.out.println(e.getMessage());
			// 트랜잭션 실패 시 rollback.
			transaction.rollback();
		} finally{
			entityManager.close();
			// EntityManager는 기능 하나당 EntityManager 객체를 사용하므로,
			// 기능 수행이 모두 끝난 경우 객체를 종료
		}
	}
}
