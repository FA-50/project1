package model.s02;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
//
@SequenceGenerator(
	name  = "SAMPLE_SEQ_GENERATOR",
	sequenceName = "SAMPLE_SEQ",
	initialValue = 1,
	allocationSize = 1
)
// Sequence를 지원하지 않는 RDBMS에서도 Sequence를 부여 가능 정의 시 다음처럼 생성됨
// [Hibernate]
// create sequence SAMPLE_SEQ start with 1 increment by 1
public class Acoount {

	@Id
	@GeneratedValue(
		strategy= GenerationType.SEQUENCE,
		generator = "SAMPLE_SEQ_GENERATOR"
	)
	private Long id;
}
