package model.cpt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// DB에서 postTable -> post_table 의 테이블이 존재하는지 검색 gn aovld
@Entity
@Table(name="article") // 클래스명이 아닌, DB에서 다른 명칭으로 지정
// @Table 설정 시 DB에서 article 의 테이블이 존재하는지 검색
public class PostTable {
	// 실제 테이블과 필드가 일치하도록 설정하기
	@Id
	private Long id;
	private String title;
	private String contents;
}
