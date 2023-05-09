package com.codingbox.springshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springshop.domain.Item;
import com.codingbox.springshop.domain.Member;
import com.codingbox.springshop.domain.Order;
import com.codingbox.springshop.domain.OrderItem;
import com.codingbox.springshop.repository.ItemRepository;
import com.codingbox.springshop.repository.MemberRepository;
import com.codingbox.springshop.repository.OrderRepository;
import com.codingbox.springshop.repository.OrderSearch;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	// 주문
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		// 엔티티 조회
		// jpa 영속성 컨텍스트 영역에 들어옴
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 주문 상품
		OrderItem orderItem = OrderItem.creatdOrderItem(item, item.getPrice(), count);
		
		// 주문 생성
		Order order = Order.createOrder(member, orderItem);
		
		// 주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}

	// 검색
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAll(orderSearch);
	}

}
