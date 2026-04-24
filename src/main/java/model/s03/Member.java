package model.s03;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected로 설정
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
		nullable = false,
		length = 50 // VARCHAR(50)
	)
	private String name;

	@Column(
		nullable = false,
		length = 255 // 비밀번호는 길이를 매우 길게하기 및 비밀번호를 평문으로 적용하면 안됨.
	)
	private String password;

	@Column(unique = true)
	private String email;

	// NULL이 들어갈 경우 무조건 0으로 반영됨
	@Column(columnDefinition = "INT DEFAULT 0")
	private int balance;
	private boolean acceptPrivacyInfo = true;

	private LocalDateTime signupAt = LocalDateTime.now();
	private LocalDateTime lastLoginAt = LocalDateTime.now();

	@OneToMany(
		mappedBy = "member",
		cascade = CascadeType.ALL,
		orphanRemoval = true // 회원 Entity 삭제 시 주소도 모두 삭제
	)
	private List<Address> addresses = new ArrayList<>();

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getBalance() {
		return balance;
	}

	public boolean isAcceptPrivacyInfo() {
		return acceptPrivacyInfo;
	}

	public LocalDateTime getSignupAt() {
		return signupAt;
	}

	public LocalDateTime getLastLoginAt() {
		return lastLoginAt;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public Member(
		String name,
		String password,
		String email,
		Address address
	){
		this.name = name;
		this.password = password;
		this.email = email;
		addresses.add(address);
		address.setMember(this);
	}
	// 편의 메서드
	protected void setAddress(Address address) {
		if(address != null){
			this.addresses.add(address);
			address.setMember(this);
		}
	}

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Order> orderList = new ArrayList<>();

	public void addOrder(Order order){
		this.orderList.add(order);
		order.setMember(this);
	}
}
