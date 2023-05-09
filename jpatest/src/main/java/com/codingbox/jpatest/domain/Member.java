package com.codingbox.jpatest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "MEMBER")
public class Member {
	
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	private String city;
	private String street;
	private String zipcode;
	@OneToMany(mappedBy = "member")
	private List<Orders> orders = new ArrayList<>();
	
	public void addOrder(Orders order) {
		order.setMember(this);
		this.orders.add(order);
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", city=" + city + ", street=" + street + ", zipcode=" + zipcode
				+ "]";
	}	
}