package com.codingbox.core2.member.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.repository.MemberRepository;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

	// 메모리 사용
	private static Map<Integer, Member> store = new HashMap<>();
	private static int sequence = 0;
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

}
