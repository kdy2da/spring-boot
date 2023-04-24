package com.codingbox.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	/**
	 * localhost:9090
	 * -> home.html(welcome page로 사용)
	 */
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
