package com.codingbox.core2.repository;

import java.util.List;

import com.codingbox.core2.member.dto.Member;

public interface MemberRepository {
	
	// 회원 저장
	public Member save(Member member);
	// 전체 조회
	public List<Member> findAll();
}
