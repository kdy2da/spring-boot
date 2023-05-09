package com.koreait.springtest5quest.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id
	@GeneratedValue	// 전략 생략하면 AUTO다
	@Column(name = "ORDER_ID")
	private Long id;
	
	// Order 입장에서 Memer는 ManyToOne이 된다.
	// Member입장에서는 Order는 여러번, Order입장에서 날 주문한 회원은 1명
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	
	private LocalDateTime orderDate;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public void changeMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id 
				+ ", orderDate=" + orderDate 
				+ ", status=" + status + "]";
	}
	
	
	
	
}













