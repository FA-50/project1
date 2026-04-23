package model.cpt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;


// SEQUENCE를 사용하지 않는 DBMS 제품군에 적용하는 것
@Entity
@SequenceGenerator(
	name = "SAMPLE_SEQ_GENERATOR",
	initialValue = 1,
	allocationSize = 1
) // SEQUENCE를 만드는 어노테이션 , 초기값과 인덱스가 몇개씩 증가할 단위를 정의
public class SequenceMapping {

	// ddl-auto 설정 시 Hibernate가 자동으로 DB Schema를 생성

	@Id
	// Hibernate는 해당 설정을 기반으로 생성됨
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "SAMPLE_SEQ_GENERATOR" // SEQUENCE 사용 시 @SequenceGenerator을 적용하여 사용해야한다.
	)
	private Long id;


}
