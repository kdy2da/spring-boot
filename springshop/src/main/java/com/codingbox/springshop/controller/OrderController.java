package com.codingbox.springshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.springshop.domain.Order;
import com.codingbox.springshop.repository.MemberRepository;
import com.codingbox.springshop.repository.OrderSearch;
import com.codingbox.springshop.service.ItemService;
import com.codingbox.springshop.service.MemberService;
import com.codingbox.springshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final MemberService memberService;
	private final ItemService itemService;
	private final OrderService orderService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		model.addAttribute("members", memberService.findMembers());
		model.addAttribute("items", itemService.findItems());
		return "order/orderForm";
	}
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId
			,@RequestParam("itemId") Long itemId
			,@RequestParam("count") int count) {
		
		// System.out.println("[itemId] : " + itemId + "[memberId] : " + memberId + "[count] : " + count);
		
		orderService.order(memberId, itemId, count);
		
		// 상세페이지 조회
		return "redirect:/";
	}
	@GetMapping("/orders")
	public String orderList(
			@ModelAttribute("orderSearch") OrderSearch orderSearch
			, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch); 
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
}
