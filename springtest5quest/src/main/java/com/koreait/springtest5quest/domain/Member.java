package com.koreait.springtest5quest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {
	
	@Id
	@GeneratedValue	// 전략 생략하면 AUTO다
	@Column(name = "MEMBER_ID")
	private Long id;
	private String name;
	
	//2-3
	// 주소
	@Embedded
	private Address address;
	//2-2
//	private String city;
//	private String street;
//	private String zipcode;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>(); 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//2-2
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getStreet() {
//		return street;
//	}
//	public void setStreet(String street) {
//		this.street = street;
//	}
//	public String getZipcode() {
//		return zipcode;
//	}
//	public void setZipcode(String zipcode) {
//		this.zipcode = zipcode;
//	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	//2-2
//	@Override
//	public String toString() {
//		return "Member [id=" + id 
//				+ ", name=" + name 
//				+ ", city=" + city 
//				+ ", street=" + street 
//				+ ", zipcode=" + zipcode + "]";
//	}
	
	//2-3
	@Override
	public String toString() {
		return "Member [id=" + id 
				+ ", name=" + name 
				+ ", address=" + getAddress().toString() + "]";
	}
	
}












