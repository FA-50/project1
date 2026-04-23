package model.s03;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Order orders;
	@ManyToOne
	private Product product;


	// 스냅샷 컬럼 : 주문 이후 Product에서 해당 데이터가 변경될 수 있으므로,
	// 주문 시점의 가격정보를 저장
	private int quantity;
	private int price;

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

}
