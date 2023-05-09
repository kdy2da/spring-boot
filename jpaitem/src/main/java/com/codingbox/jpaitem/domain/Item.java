package com.codingbox.jpaitem.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Item {

	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems
		= new ArrayList<>();
	
	public void addOrderItem(OrderItem orderItem) {
		orderItem.setItem(this);
		this.orderItems.add(orderItem);
	}
	
	private String name;
	private int price;
	private int stockQuantity;
}
