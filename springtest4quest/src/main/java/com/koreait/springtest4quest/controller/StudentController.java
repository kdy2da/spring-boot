package com.koreait.springtest4quest.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.springtest4quest.repository.Student;
import com.koreait.springtest4quest.repository.StudentRepository;

@Controller
@RequestMapping("/student/students")
public class StudentController {

private final StudentRepository studentRepository;
	
	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	

	@GetMapping
	public String students(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "student/students";
	}
	
	@GetMapping("/add")
	public String addForm(Model model) {
		return "student/studentAddForm";
	}
	
	@PostMapping("/add")
	public String save( @ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
		Student savedStudent = studentRepository.save(student);
		
		redirectAttributes.addAttribute("studentId", savedStudent.getStudentId());
		redirectAttributes.addAttribute("status",true);					
		
		return "redirect:/student/students/{studentId}" ;
	}
	
	@GetMapping("/{studentId}")
	public String student(@PathVariable long studentId  ,Model model) {
		Student student = studentRepository.findById(studentId);
		model.addAttribute("student",student);
		return "student/student";
	}
	
	
	@GetMapping("/{studentId}/edit")
	public String editForm(@PathVariable Long studentId, Model model){
		Student student = studentRepository.findById(studentId);
		model.addAttribute("student", student);
		return "student/studentEditForm";
	}
	
	@PostMapping("/{studentId}/edit")
	public String edit(@PathVariable Long studentId, @ModelAttribute Student student){
		studentRepository.update(studentId, student);
		return "redirect:/student/students/{studentId}";
	}
	
	@PostConstruct
	public void init() {
		studentRepository.save(new Student("학생A", 20, 4, "01012345671", "역삼1동"));
		studentRepository.save(new Student("학생B", 22, 5, "01012345672", "역삼2동"));
		studentRepository.save(new Student("학생C", 23, 6, "01012345673", "역삼3동"));
	}
	
}
