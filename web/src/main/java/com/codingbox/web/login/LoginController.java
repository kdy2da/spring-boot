package com.codingbox.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;

	@GetMapping
	public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
		return "login/loginForm";
	}
	@PostMapping
	public String login(@ModelAttribute LoginForm loginForm) {
		
		Member loginMember 
			= loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		
		if( loginMember == null ) {
			// 로그인 실패
			return "login/loginForm";
		}
		// 로그인 성공
		return "redirect:/";
	}
}
