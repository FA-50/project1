package model.s01;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MappingEntityManagerFactory1 {
	public static void main(String[] args) {
		// <persistence-unit name="hibernate-exp-2">의 xml 설정을 기반으로 EntityManagerFactory 생성 시
		try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-exp-3")){
			// EntityManagerFactory는 내부적으로 Connection Pool을 가지고 있다.
			// 해당 Connection Pool의 Connection을 생성 후 EntityManager 생성

			// 구동 시  EntityManagerFactory를 생성하여 <persistence-unit name="hibernate-exp-2"> 내부의
			// <class> 태그 내 등록된 Entity Class를 기반으로 Hibernate가 DDL-AUTO되어 Table을 생성

		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
