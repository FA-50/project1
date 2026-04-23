package model.s03;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private String zipcode;
	@Column(nullable=false)
	private String city;
	@Column(nullable=false)
	private String street;

	// 외래키의 주인
	@ManyToOne(optional=false)
	@JoinColumn(nullable=false, name = "member_id")
	private Member member;

	protected void setMember(Member member) {
		this.member = member;
	}
}
