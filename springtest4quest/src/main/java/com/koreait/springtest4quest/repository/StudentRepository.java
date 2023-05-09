package com.koreait.springtest4quest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

	private static final Map<Long, Student> store = new HashMap<>();
	private static long sequence = 0L;
	
	// 저장
	public Student save(Student student) {
		student.setStudentId(++sequence);
		store.put(student.getStudentId(), student);
		return student;
	}
	
	// id로 찾기
	public Student findById(Long studentID) {
		return store.get(studentID);
	}
	
	// 전체 검색
	public List<Student> findAll(){
		return new ArrayList<Student>(store.values());
	}
	
	// 수정
	public void update(Long studentId, Student updateParam) {
		Student findStudent = findById(studentId);
		findStudent.setStudentName(updateParam.getStudentName());
		findStudent.setAge(updateParam.getAge());
		findStudent.setSubject(updateParam.getSubject());
		findStudent.setPhone(updateParam.getPhone());
		findStudent.setAddr(updateParam.getAddr());
	}
	
}
