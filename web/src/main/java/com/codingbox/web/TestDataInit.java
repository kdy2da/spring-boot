package com.codingbox.web;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.codingbox.web.domain.member.Member;
import com.codingbox.web.domain.member.MemberRepository;
import com.codingbox.web.item.Item;
import com.codingbox.web.item.ItemRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataInit {
	
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	@PostConstruct
	public void init() {
		Member member = new Member();
		member.setLoginId("test");
		member.setPassword("1234");
		member.setName("테스트");
		memberRepository.save(member);
		itemRepository.save(new Item("홍길동", 10000, 2));
		itemRepository.save(new Item("임꺽정", 30000, 3));
		itemRepository.save(new Item("이순신", 50000, 1));
		System.out.println("초기화 메서드");
	}
}
