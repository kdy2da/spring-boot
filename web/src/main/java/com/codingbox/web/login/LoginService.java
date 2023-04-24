package com.codingbox.web.login;

import org.springframework.stereotype.Service;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final MemberRepository memberRepository;
	
	public Member login(String loginId, String password) {
		Member member = memberRepository.findByLoginId(loginId);
		if(member != null && member.getPassword().equals(password)) {
			// 아이디는 맞고, 패쓰워드가 맞는 경우 ( 로그인 성공 )
			return member;
		} else {
			// 아이디가 틀리거나 패쓰워드가 틀린 경우 ( 로그인 실패 )
			return null;
		}
	}
}
