package com.codingbox.jpaitem.embedded;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
@Table(name = "ORDERS")
public class Orders {
	
	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
//	@Column(name = "MEMBER_ID")
//	private Long memberId;
	private LocalDateTime orderDate;
	private String status;
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private com.codingbox.jpaitem.embedded.Member member;
	
	public void changeMember(com.codingbox.jpaitem.embedded.Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	public void setMember(com.codingbox.jpaitem.embedded.Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderDate=" + orderDate + ", status=" + status + "]";
	}
}
