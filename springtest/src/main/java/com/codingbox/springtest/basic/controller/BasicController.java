package com.codingbox.springtest.basic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbox.springtest.student.Student;
import com.codingbox.springtest.student.StudentRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/students")
@RequiredArgsConstructor
public class BasicController {
	
	private final StudentRepository studentRepository;
	
	@GetMapping
	public String students(Model model) {
		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		return "basic/students";
	}
	
	@GetMapping("/{studentId}")
	public String student(@PathVariable Long studentId, Model model) {
		Student student = studentRepository.findById(studentId);
		model.addAttribute(student);
		return "basic/student";
	}
	@GetMapping("/{studentId}/edit")
	public String editForm(@PathVariable Long studentId, Model model) {
		Student student = studentRepository.findById(studentId);
		model.addAttribute(student);
		return "basic/studentEditForm";
	}
	@PostMapping("/{studentId}/edit")
	public String edit(@ModelAttribute Student student,
			@PathVariable Long studentId) {
		studentRepository.update(studentId, student);		
		return "redirect:/basic/students/{studentId}";
	}
	@GetMapping("/add")
	public String addForm() {
		return "basic/studentAddForm";
	}
	@PostMapping("/add")
	public String add(@ModelAttribute Student newStudent, RedirectAttributes redirectAttributes) {
		Student student = studentRepository.save(newStudent);
		redirectAttributes.addAttribute("studentId",student.getStudentId());
		redirectAttributes.addAttribute("status",true);
		return "redirect:/basic/students/{studentId}";
	}
}
