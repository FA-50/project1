package model.s03;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Members")
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
	private List<Address> addresses;

	public Member(
		String name,
		String password,
		String email,
		Address address
	){
		this.name = name;
		this.password = password;
		this.email = email;
		setAddress(address);
	}

	// 편의 메서드
	protected void setAddress(Address address) {
		if(address != null){
			this.addresses.add(address);
			address.setMember(this);
		}
	}
}
