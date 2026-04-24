package model.s03;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.constant.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@ManyToOne
	@JoinColumn(name="member_id", nullable = false)
	private Member member;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

	public Order(Member member){
		this.member = member;
		this.orderStatus = OrderStatus.ORDERED;
	}

	public void addOrderItem(OrderItem oi){
		this.orderItems.add(oi);
		oi.setOrders(this);
	}

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	// 동시성 제어 시 낙관적락 기준을 도출 시 사용하는 메서드
	public void updateClock(){
		this.updatedDate = LocalDateTime.now();
	}

	public void setOrderStatus(OrderStatus orderStatus){
		this.orderStatus = orderStatus;
	}
}
