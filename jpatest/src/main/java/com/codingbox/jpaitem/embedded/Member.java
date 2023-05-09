package com.codingbox.jpaitem.embedded;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue // 전략 생략하면 AUTO
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "member")
	private List<com.codingbox.jpaitem.embedded.Orders> orders = new ArrayList<>();
	
	public void addOrder(com.codingbox.jpaitem.embedded.Orders order) {
		order.setMember(this);
		this.orders.add(order);
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", address=" + address.toString() + "]";
	}

	
}













