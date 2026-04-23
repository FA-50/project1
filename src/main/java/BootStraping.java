import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BootStraping {
	public static void main(String[] args) {

		// 설정된 영속성 단위 이름의 EntityManagerFactory 객체를 생성
		// "hibernate-exp-1"은 persistence.xml의 `<persistence-unit name="hibernaete-exp-1">`에서 정의된 이름으로 참조하여
		// 해당 설정을 기반으로 EntityManagerFactory 객체를 생성
		EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("hibernate-exp-1");




	}
}
