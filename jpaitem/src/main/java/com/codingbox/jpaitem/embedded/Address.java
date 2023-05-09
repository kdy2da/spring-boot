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
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
	// 파라미터 있는 생성자를 선언 시,
	// 기본 생성자 필수적으로 선언 해줘야 한다.
	public Address() {}
	
}
