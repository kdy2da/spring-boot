package com.codingbox.core2.member.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
//	MemberRepository memberRepository
//		= new MemoryMemberRepository();
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원 가입
	public int join(Member member) {
		memberRepository.save(member);
		return member.getId();
	}
	// 전체 회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
}
