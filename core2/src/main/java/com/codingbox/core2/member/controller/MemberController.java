package com.codingbox.core2.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.member.dto.MemberFormDTO;
import com.codingbox.core2.member.service.MemberService;

@Controller
public class MemberController {
	/*
	 * Controller 가  service 를 의존한다라고 표현한다.
	 * Service는 여러 Controller 가 가져다 쓸 수 있기 때문에
	 * Spring Container 에 등록을 해야한다.
	 */
//	MemberService service = new MemberService();
	
	// 스프링 스럽게 작업 방법
	private final MemberService memberService;
	
	/*
	 *  MemberController 가 생성될때, 생성자를 호출해준다.
	 *  즉, service까지 생성해서 자동으로 호출해준다.
	 *  서버 기동시 연결 실패시 에러가 발생한다.
	 *  -> 기존은 테스트를 통해서만 service가 오류가 나는 것을 확인할 수 있었다.
	 */
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/memebers/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	@PostMapping(value = "/members/new" )
	public String create(MemberFormDTO memberForm) {
		Member member = new Member();
		member.setName(memberForm.getName());
		this.memberService.join(member);
		return "redirect:/";
	}
	@GetMapping("/memebers")
	public String list(Model model) {
		List<Member> members = this.memberService.findMembers();
		model.addAttribute("members",members);
		return "members/memberList";
	}
}
