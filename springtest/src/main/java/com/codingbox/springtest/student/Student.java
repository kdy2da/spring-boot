package com.codingbox.springtest.student;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class Student {
	private Long studentId;
	private String studentName;
	private Integer age;
	private Integer subject;
	private String phone;
	private String addr;
}
