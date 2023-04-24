package com.codingbox.core3.thymeleaf.bean;


import org.springframework.stereotype.Component;

@Component("helloBean")
public class HelloBean {
	public String hello(String data) {
		
		return "spring data " + data;
	}
}
