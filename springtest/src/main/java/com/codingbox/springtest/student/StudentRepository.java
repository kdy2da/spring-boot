package com.codingbox.springtest.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
	private static final Map<Long, Student> store = new HashMap<>();
	private static long sequence = 0;
	
	public Student save(Student student) {
		student.setStudentId(++sequence);
		store.put(student.getStudentId(), student);
		return student;
	}
	
	public Student findById(Long studentId) {
		return store.get(studentId);
	}
	
	public List<Student> findAll() {
		return new ArrayList<Student>(store.values());
	}
	
	public void update(Long studentId, Student updateParam) {
		Student findStudent = findById(studentId);
		findStudent.setAddr(updateParam.getAddr());
		findStudent.setAge(updateParam.getAge());
		findStudent.setSubject(updateParam.getSubject());
		findStudent.setPhone(updateParam.getPhone());
		findStudent.setStudentName(updateParam.getStudentName());
	}
}
