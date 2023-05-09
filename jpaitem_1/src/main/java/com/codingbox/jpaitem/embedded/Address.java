package com.codingbox.jpaitem.embedded;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	public Address(String city, 
			String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	// 기본 생성자는 반드시 있어야한다
	public Address() {}
}












