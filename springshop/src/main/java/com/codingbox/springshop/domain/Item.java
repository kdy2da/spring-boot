package com.codingbox.springshop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.codingbox.springshop.exception.NotEnoughStockException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	// stock 감소
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			// 수량 부족한 경우
			// 사용자 지정 Exception
			throw new NotEnoughStockException("need more stock");
		}
		// 수량 충분한 경우
		this.stockQuantity = restStock;
	}
	// stock 증가
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
}
