package com.codingbox.jpql;

import javax.persistence.Embedded;
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
@Table(name = "Orders")
public class Order {

	@Id @GeneratedValue
	private Long id;
	private int orderAmount;
	@Embedded
	private Address address;
	
	// 연관관계(단방향만 설정함)
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
}
