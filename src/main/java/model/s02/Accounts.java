package model.s02;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
@TableGenerator(
	name = "SAMPLE_SEQ_TABLE_GENERATOR",
	table = "SAMPLE_SEQUENCE_CHECK",
	pkColumnName = "SAMPLE_ACCOUNT_SEQ",
	allocationSize = 1
)
// [Hibernate]
// create table SAMPLE_SEQUENCE_CHECK (
// 	next_val bigint,
// 	SAMPLE_ACCOUNT_SEQ varchar(255) not null,
// primary key (SAMPLE_ACCOUNT_SEQ)
//     )
// 			[Hibernate]
// insert into SAMPLE_SEQUENCE_CHECK(SAMPLE_ACCOUNT_SEQ, next_val) values ('Accounts',0)
// 해당 TABLE 전략 용도의 테이블을 생성 및 초기 데이터를 입력 -> Accounts 테이블의 데이터 입력 시 다음 행 부터 1부터 증가하면서 입력
public class Accounts {
	@Id
	@GeneratedValue(
		strategy = GenerationType.TABLE,
		generator = "SAMPLE_SEQ_TABLE_GENERATOR"
	)
	private Long id;

	private String name;


}
