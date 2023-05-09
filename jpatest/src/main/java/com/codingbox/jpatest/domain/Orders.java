package com.codingbox.jpatest.domain;

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

@Entity
@Getter @Setter
@Table(name = "ORDERS")
public class Orders {
	
	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	private LocalDateTime orderDate;
	private String status;
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	public void changeMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderDate=" + orderDate + ", status=" + status + "]";
	}
}
