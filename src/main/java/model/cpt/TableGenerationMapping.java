package model.cpt;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
@TableGenerator(
	name = "SAMPLE_SEQ_TABLE_GENERATOR",
	table = "SAMPLE_SEQUENCE_CHECK",	// 해당 Generator의 Table이 DB에서 생성
	pkColumnName = "SAMPLE_ACCOUNT_SEQ", // 생성될 테이블의 PK명 지정
	allocationSize = 1
)
public class TableGenerationMapping {

	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE,
		generator = "SAMPLE_SEQ_TABLE_GENERATOR"
	)
	private Long id;
}
