package com.codingbox.web.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	
	/*
	 * @{/members/add} url이 매핑되는 컨트롤러
	 * - /members	: 	controller 단에서 매핑
	 * - /add		: 	method 단에서 매핑
	 * addForm() 메소드 이름 설정
	 * - members/addMemberForm 으로 view 
	 */
	
	/*
	 * @ModelAttribute("member") Member member
	 * -> model.addAttribute("member", new Member());
	 */
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member) {
		return "members/addMemberForm";
	}
	@PostMapping("/add")
	public String add(@ModelAttribute Member member) {
		memberRepository.save(member);
		return "redirect:/";
	}
}
