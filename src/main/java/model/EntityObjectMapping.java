package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// DB 개체 ( Entity )를 정의
// @Entity로 선언된 클래스는 반드시 기본생성자를 정의해야한다.
// 실무에서 기본생성자는 protected를 선언하여 기본생성자가 호출되지않도록 설정하여 DB에 Null값의 Record가 생성되지 않도록 보호해야한다.

// 자바에서 생성자가 따로 정의되있지 않은 경우 JVM에 의해 컴파일 시점에서 기본 생성자를 자동 생성
// 단, 생성자( 사용자 정의 생성자 )를 오버로딩한 경우 자동생성하지 않으므로, 따로 기본 생성자를 정의해야한다.
// 해당 기본 생성자는 public / protected로 선언해야한다.
@Entity
public class EntityObjectMapping {

	// Entity Class 는 반드시 @Id가 선언된 식별필드 역할의 PK가 존재해야한다.
	// 해당 필드를 통해 ORM의 영속성 컨텍스트에서 식별됨
	// JPA BUDDY : JPA 에 관련된 기능(Query Method 등)을 사용할 수 있음.
	// @Id 필드는 자동으로 GeneratedValue를 통해 값할당이 수행되므로 Setter를 적용하면 안됨.
	@Id
	@GeneratedValue
	private String id;

	// 테이블 내 컬럼으로 읽음
	private String name;

	protected EntityObjectMapping() {}

	public EntityObjectMapping(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
