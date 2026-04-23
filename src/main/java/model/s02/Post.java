package model.s02;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Table(name="articles")
public class Post {

	// 자료형은 Null이 들어갈 수 있으므로, Wrapper Class로 설정
	@Id
	private Integer id;

	private String title;

	private String contents;
}
