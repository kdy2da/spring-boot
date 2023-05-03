package com.codingbox.web.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
		return "login/loginForm";
	}
//	@PostMapping("/login")
	public String login(@ModelAttribute LoginForm loginForm
			, Model model, RedirectAttributes redirAttrs, HttpServletResponse response) {
		/**
		 *  Model : forward 방식 으로 데이터 전송 방법
		 *  RedirectAttributes : redirect 방식 으로 데이터 전송 방법
		 */
		
		Member loginMember 
			= loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		// 로그인 성공
		// 쿠키에다가 시간 정보를 주지 않으면 세션 쿠키이다.
		Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
		response.addCookie(idCookie);
		redirAttrs.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
	}
	
//	@PostMapping("/logout")
	public String logout(HttpServletResponse response) {
		expiredCookie(response, "memberId");
		System.out.println("쿠키 삭제");
		return "redirect:/";
	}
	
	private void expiredCookie(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	///////////////////////// 로긴정보 세션으로 처리하기 /////////////////////////
//	@PostMapping("/login")
	public String loginV2(@ModelAttribute LoginForm loginForm
			, Model model, RedirectAttributes redirAttrs, HttpServletRequest request) {
		/**
		 *  Model : forward 방식 으로 데이터 전송 방법
		 *  RedirectAttributes : redirect 방식 으로 데이터 전송 방법
		 */
		
		Member loginMember 
			= loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		// 로그인 성공
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		System.out.println("세션 생성");
		redirAttrs.addFlashAttribute("msg", "로그인 성공");
		return "redirect:/";
	}
	
	@PostMapping("/logout")	
	public String logoutV2(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println("세션 삭제");
			//session.removeAttribute(SessionConst.LOGIN_MEMBER);
			session.invalidate();
		}
		return "redirect:/";
	}
	
	// 로그인 후 요청한 페이지대로 이동하기(사용자의 편의성을 위한 로직 추가)
	@PostMapping("/login")
	public String loginV3(@ModelAttribute LoginForm loginForm
			, Model model, RedirectAttributes redirAttrs, HttpServletRequest request
			, @RequestParam(defaultValue = "/") String redirectURL) {
		/**
		 *  Model : forward 방식 으로 데이터 전송 방법
		 *  RedirectAttributes : redirect 방식 으로 데이터 전송 방법
		 */
		
		Member loginMember 
			= loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		
		if( loginMember == null ) {
			// 로그인 실패
			model.addAttribute("msg", "로그인 실패");
			return "login/loginForm";
		}
		// 로그인 성공
		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
		System.out.println("세션 생성");
		redirAttrs.addFlashAttribute("msg", "로그인 성공");
		return "redirect:" + redirectURL;
	}
}
