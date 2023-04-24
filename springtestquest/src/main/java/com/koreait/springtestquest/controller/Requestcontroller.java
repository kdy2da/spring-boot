package com.koreait.springtestquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Requestcontroller {
	
	@RequestMapping("/param/home")
	public String sendParameter() {
		return "param/home";
	}
	@GetMapping("/param/get")
	public String sendParameter(@RequestParam Integer answer, Model model) {
		model.addAttribute("answer",answer);
		return "param/getresult";
	}
	@PostMapping("/param/post")
	public String postResult(@RequestParam(defaultValue = "guest") String user_name,
			@RequestParam(defaultValue = "-1") Integer user_age, Model model) {
		model.addAttribute("user_name",user_name);
		model.addAttribute("user_age",user_age);
		return "param/postresult";
	}
}
