package com.codingbox.core2.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// localhost:9090 으로 호출하면 home() 호출
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
