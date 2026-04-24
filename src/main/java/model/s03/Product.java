package model.s03;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer price;

	@Column(columnDefinition = "INT DEFAULT 0")
	private int stock;

	// 타임스탬프 : 동시성 제어 시 Lock 설정할때 Version 락 설정 시 사용
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// JPA는 @ManyToMany 지정 시 Hibernate가 자동으로 Mapping table을 생성.
	// @JoinTable(name = "지정할매핑테이블이름", joinColumns(name="조인할ON컬럼명"))
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "products_categories",
		joinColumns = @JoinColumn(name = "product_id"),
		// 반대편에서 조인할 ON컬럼명
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories = new ArrayList<>();

	public Product(String name, Integer price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public void addCategory(Category category){
		if(category!=null){
			this.categories.add(category);
			category.getProducts().add(this);
		}
	}


}
