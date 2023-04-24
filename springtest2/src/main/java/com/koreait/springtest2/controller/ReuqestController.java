package com.koreait.springtest2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReuqestController {

	@GetMapping("/param/home")
	public String home() {
		return "param/home";
	}
	
	@GetMapping("/param/get")
	public String paramGet(@RequestParam(name = "answer") int answer, Model model) {
		String result = null;
		
		if( answer == 300 ) {
			result = "정답입니다."; 
		} else {
			result = "오답입니다.";
		}
		
		model.addAttribute("answer", answer);
		model.addAttribute("result", result);
		
		return "param/getresult";
	}
	
	@PostMapping("/param/post")
	public String paramPost(@RequestParam(name = "user_name", defaultValue = "guest") String user_name,
							@RequestParam(name = "user_age", defaultValue = "-1") int user_age, Model model) {
		
		model.addAttribute("user_name", user_name);
		model.addAttribute("user_age", user_age);
		
		return "param/postresult";
	}
	
}
