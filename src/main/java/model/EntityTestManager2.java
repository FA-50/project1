package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s01.Member;

public class EntityTestManager2 {
	private static final EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("hibernate-exp-5");
	}

	public static void main(String[] args) {
		String targetName = "user1";

		saveMember(targetName);

		Member foundedMember = findMember(1L);
		System.out.println(foundedMember.getName());
		// 검증
		System.out.println(" result = " + targetName.equals(foundedMember.getName()));

		String changedName = "user10";

		updateMember(1L,changedName);
		Member member2 = findMember(1L);
		System.out.println(member2.getName());

		// if(member2.getName().equals(foundedMember.getName()))
		// 	System.out.println("ok");
		// else
		// 	throw new IllegalArgumentException("Not Equals");

		
		deleteMember(1L);
		Member foundedDeletedMember = findMember(1L);
		if(foundedDeletedMember != null) throw new IllegalArgumentException("삭제 안됨");
		else
			System.out.println("removed");
	}
// 한 기능 당 하나의 EntityManager를 할당 및 종료
	private static void saveMember(String targetName) {
		EntityManager em = emf.createEntityManager();
		// EntityManager의 트랜잭션 객체 생성
		EntityTransaction tx = em.getTransaction();

		// 트랜잭션 작업 시 try ~ catch로 작성
		// 예외 발생 시 rollback / 기능이 모두 종료된 경우 em 종료
		try{
			tx.begin();
			em.persist(new Member(targetName));
			tx.commit();
		} catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
		}
	}

	private static Member findMember(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try{
			return em.find(Member.class, id);

		} finally{
			em.close();
		}
	}

	private static void updateMember(Long id, String newName) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		// ORM 단점 : UPDATE 수행 시 선행적으로 무조건 SELECT 문을 추가로 실행해야하는
		// 단점이 존재
		try{
			tx.begin();

			Member member = em.find(Member.class, id);

			if(member != null) member.setName(newName);

			// 영속화된 객체를 수정 시 초기 객체의 SNAPSHOT을 기준으로
			// 변경을 수행 후 flush를 수행 시 객체변경사항을 EntityManager 가 검사 후
			// 변경사항을 DB에 반영

			tx.commit();
		} finally{
			em.close();
		}
	}

	private static void deleteMember(Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		// ORM 단점 : UPDATE 수행 시 선행적으로 무조건 SELECT 문을 추가로 실행해야하는
		// 단점이 존재
		try{
			tx.begin();

			// 삭제 = Update 와 동일하게 선행적으로 SELECT를 통해 Member 데이터를 가져옴
			Member member = em.find(Member.class, id);
			if(member != null) em.remove(member);

			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		}finally
		{
			em.close();
		}
	}
}
