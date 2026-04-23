package model.cpt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ColumnAndGeneration {

	// Hibernate는 Entity객체를 통해 데이터 삽입 시 데이터에 대한 무결성을 검사하지 않음
	// 사용자가 직접 무결성을 검사하는 로직을 작성해야함.

	@Id
	@Column(name = "products_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 생성전략 : GenerationType.IDENTITY = AutoIncrement : 주로 AutoIncrement를 사용하는 Oracle 등에서 사용하는 방식,
	// GenerationType.UUID / GenerationType.SEQUENCE : 주로 MySQL, PostgreSQL 에서 선언하는 방식
	// GenerationType.TABLE : SEQUENCE
	// GenerationType.AUTO : 자동으로 알맞은 값으로 적용 (@GeneratedValue는 기본적으로 AUTO )
	private Long id; // BIGINT

	@Column(name = "products_name", columnDefinition = "TEXT")
	private String name; // 자료형 : String인 경우 VARCHAR(255)로 설정
												// 이때, Hibernate에서 DDL-auto을 통해 Table 생성 시 columnDefinition="DB타입" 속성에 따라서
												// 해당 자료형으로 DB 필드가 생성



	@Column(name = "products_description", nullable = false) // 해당 DB필드에 NULL값이 안들어감.
	private String description;
}
