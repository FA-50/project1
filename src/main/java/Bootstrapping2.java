import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;

public class Bootstrapping2 {
	public static void main(String[] args) {
		
		// persistence.xml 의 설정 없이 직접 JAVA에서 JPA 설정하는 방법
		
		PersistenceConfiguration cfg = new PersistenceConfiguration("hibernate-exp")
			// persistence.xml 의 <property> 태그에 대응
			.property(PersistenceConfiguration.JDBC_DRIVER, "org.h2.Driver")
			.property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:test-db")
			.property(PersistenceConfiguration.JDBC_USER, "sa")
			.property(PersistenceConfiguration.JDBC_PASSWORD, "");
		
		// EntityManagerFactory 생성하기
		EntityManagerFactory emf = cfg.createEntityManagerFactory();


		// 자료구조를 사용해서 접속정보를 정의한 불변객체를 생성 후 설정하기
		Map<String, String> sa = Map.of(
			PersistenceConfiguration.JDBC_DRIVER, "org.h2.Driver",
			PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:test-db",
			PersistenceConfiguration.JDBC_USER, "sa",
			PersistenceConfiguration.JDBC_PASSWORD, ""
		);

		// 설정된 영속성 단위 이름의 임의의 EntityManagerFactory 객체를 생성
		PersistenceConfiguration cfg2 = new PersistenceConfiguration("hibernate-exp-2")
			.properties(sa);

		EntityManagerFactory emf2 = cfg2.createEntityManagerFactory();

	}
}
