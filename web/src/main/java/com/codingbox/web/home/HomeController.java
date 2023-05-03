package com.codingbox.web.home;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;
import com.codingbox.web.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final MemberRepository memberRepository;
	/**
	 * localhost:9090
	 * -> home.html(welcome page로 사용)
	 */
//	@RequestMapping("/")
	public String home() {
		return "home";
	}
	/*
	 *  Cookie 로 처리하는 경우
	 *  로그인 처리까지 되는 home 화면 확인
	 *  required = false : 로그인 안한 사용자도 들어와야 한다.
	 */
//	@GetMapping("/")
	public String homeLogin(
			@CookieValue(name="memberId", required=false) Long memberId
			, Model model) {
		// 1. 로그인한 사용자가 아니라면 home으로 보낸다.
		if( memberId == null) {
			System.out.println("1. 로그인한 사용자가 아니라면 home으로 보낸다.");
			return "home";
		}
		// 쿠키값을 네트워크 해킹을 통해 조작이 충분히 가능하므로 DB를 한번 더 조회해서 확인해주는 로직이 필요하다.
		// 2. db 조회를 한 후, 사용자가 없으면 다시 home으로 보낸다.(쿠키에 데이터가 존재하지만, 현재 DB 에 없을 경우)
		Member member = memberRepository.findById(memberId);
		
		if(member == null) {
			System.out.println("2. db 조회를 한 후, 사용자가 없으면 다시 home으로 보낸다.");
			return "home";
		} 
		
		// 3.
		// 로그인에 성공한 사람은
		// db 조회 결과값을 담아 loginHome 화면으로 이동
		model.addAttribute("member",member);
		return "loginHome";
	}
	
	/*
	 * Session 으로 처리하는 경우
	 */
//	@GetMapping("/")
	public String homeLoginV2(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		
		// session null 값이면 return home
		if(session == null) {
			return "home";
		}
				
		// session의 LOGIN_MEMBER값으로 조회해서
		// 회원의 데이터가 없으면 return home
		Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
		
		if(loginMember == null) {
			return "home";
		}
		
		// 세션이 유지되면 model 에 담아서 return loginHome
		model.addAttribute("member",loginMember);
		return "loginHome";
	}
	@GetMapping("/")
	public String homeLoginV3(
			// session attribute 를 뒤져서 member 에 값을 넣어준다.
			@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required=false) Member loginMember
			, Model model) {
		
		// session의 LOGIN_MEMBER값으로 조회해서
		// 회원의 데이터가 없으면 return home
		
		if(loginMember == null) {
			return "home";
		}
		
		// 세션이 유지되면 model 에 담아서 return loginHome
		model.addAttribute("member",loginMember);
		return "loginHome";
	}
}
