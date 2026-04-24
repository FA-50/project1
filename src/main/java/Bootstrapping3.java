import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

import jakarta.persistence.EntityManagerFactory;

public class Bootstrapping3 {
	public static void main(String[] args) {
		// Hibernate Spec 사용하기
		
		// BootStraping1 , BootStraping2, BootStraping3 중 하나의 방법을 선택하여 EntityManagerFactory를 생성

		HibernatePersistenceConfiguration hfg = new HibernatePersistenceConfiguration(
			"hibernate-exp-4");
		hfg
			.jdbcDriver("org.h2.Driver")
			.jdbcUrl("jdbc:h2:mem:test-db")
			.jdbcUsername("sa")
			.jdbcPassword("");
		EntityManagerFactory emf4 = hfg.createEntityManagerFactory();

		// EntityManagerFactory를 통해 EntityManager 객체를 생성하고,
		// EntityManager객체를 통해 영속성 컨텍스트에 Entity Class 객체를 넣어서 생애주기를 관리

		// EntityManagerFactory는 내부적으로 Connection Pool을 가지고 있다.
		// 해당 Connection Pool의 Connection을 생성 후 EntityManager 생성
	}
}
