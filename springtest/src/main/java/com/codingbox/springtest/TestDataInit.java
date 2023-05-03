package com.codingbox.springtest;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.codingbox.springtest.student.Student;
import com.codingbox.springtest.student.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataInit {
	
	private final StudentRepository studentRepository;
	
	@PostConstruct
	public void init() {
		Student st1 = new Student();
		st1.setStudentName("학생A");
		st1.setAge(20);
		st1.setPhone("01040072415");
		st1.setAddr("서울");
		st1.setSubject(1);
		Student st2 = new Student();
		st2.setStudentName("학생B");
		st2.setAge(25);
		st2.setPhone("01012345678");
		st2.setAddr("부산");
		st2.setSubject(2);
		Student st3 = new Student();
		st3.setStudentName("학생C");
		st3.setAge(30);
		st3.setPhone("01087654321");
		st3.setAddr("세종");
		st3.setSubject(3);
		
		studentRepository.save(st1);
		studentRepository.save(st2);
		studentRepository.save(st3);
		
		System.out.println("초기화 메서드");
	}
}
