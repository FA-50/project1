package model.s02;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@Column(name = "product_id")
	@GeneratedValue
	private Long id;
	// GenerationType.AUTO 인 경우 JPA 가 사용하는 DBMS에 적합한 PK 생성 전략으로 설정
	// H2 DB에서 각 DBMS에 맞는 MODE를 설정 시 해당 DBMS Dialect로 사용할 수 있음

	@Column(nullable = false)
	private String name;

	@Column(columnDefinition = "TEXT")
	private String description;
	// nullable=false를 설정해도 데이터 입력 시 무결성을 검사하지 않으므로, Spring Validation을 통해 사용자가
	// 무결성을 검사하는 로직을 작성해야한다.

}
