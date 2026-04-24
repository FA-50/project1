package model.s03;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order orders;
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public OrderItem(Product product, int quantity){
		if (product == null)
			throw new IllegalArgumentException("실행은 반드시 입력되어야한다.");
		this.product = product;
		this.quantity = quantity;
		// 동적으로 결정
		this.price = product.getPrice();
		this.createdDate = LocalDateTime.now();
		this.updatedDate = LocalDateTime.now();
	}

	// 스냅샷 컬럼 : 주문 이후 Product에서 해당 데이터가 변경될 수 있으므로,
	// 주문 시점의 가격정보를 저장
	private int quantity;
	private int price;

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;



}
