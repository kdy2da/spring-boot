package com.codingbox.core3.domain.web.basic;

public class HelloData {
	
	private String username;
	private int age;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "HelloData [username=" + username + ", age=" + age + "]";
	}
	
	
	
}
