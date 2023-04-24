package com.codingbox.core3.thymeleaf.basic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingbox.core3.thymeleaf.data.User;

@Controller
@RequestMapping("/basic")
public class BasicController {
	
	@GetMapping("text-basic")
	public String textBasic(Model model) {
		model.addAttribute("data","<b>Hello Spring!!</b>");
		return "basic/text-basic";
	}
	
	@GetMapping("text-unescaped")
	public String textUnescaped(Model model) {
		model.addAttribute("data","<b>Hello Spring!!</b>");
		return "basic/text_unescaped";
	}
	@GetMapping("variable")
	public String textVariable(Model model) {
		User userA = new User("userA", 10);
		User userB = new User("userB", 20);
		
		List<User> list = new ArrayList<>();
		list.add(userA);
		list.add(userB);
		
		Map<String, User> map = new HashMap<>();
		map.put("userA", userA);
		map.put("userB", userB);
		
		model.addAttribute("user", userA);
		model.addAttribute("users", list);
		model.addAttribute("userMap", map);
		
		return "basic/variable";
	}
	@GetMapping("basic-objects")
	public String basicObject(HttpSession session
			, @RequestParam String paramData
			, Model model) {
		session.setAttribute("sessionData", "helloSession");
		return "basic/basic-objects";
	}
	@GetMapping("date")
	public String basicDate(Model model) {
		model.addAttribute("localDateTime", LocalDateTime.now());
		return "basic/date";
	}
	@GetMapping("link")
	public String basicLink(Model model) {
		model.addAttribute("param1", "data1");
		model.addAttribute("param2", "data2");
		return "basic/link";
	}
	@GetMapping("literal")
	public String basicLiteral(Model model) {
		model.addAttribute("data", "Spring");
		return "basic/literal";
	}
	@GetMapping("operation")
	public String basicOperation(Model model) {
		model.addAttribute("data", "Spring");
		return "basic/operation";
	}
	@GetMapping("attribute")
	public String basicAttribute(Model model) {
		model.addAttribute("data", "Spring");
		return "basic/attribute";
	}
	@GetMapping("each")
	public String basicEach(Model model) {
		addUsers(model);
		return "basic/each";
	}
	@GetMapping("condition")
	public String basicCondition(Model model) {
		addUsers(model);
		return "basic/condition";
	}
	@GetMapping("comments")
	public String basicComments(Model model) {
		model.addAttribute("data","Spring");
		return "basic/comments";
	}
	@GetMapping("block")
	public String basicBlock(Model model) {
		addUsers(model);
		return "basic/block";
	}
	@GetMapping("javascript")
	public String basicJavascript(Model model) {
		model.addAttribute("user", new User("userD",40));
		addUsers(model);
		return "basic/javascript";
	}
	
	private void addUsers(Model model) {
		List<User> list = new ArrayList<>();
		list.add(new User("userA",10));
		list.add(new User("userB",20));
		list.add(new User("userC",30));
		model.addAttribute("users", list);
	}
}
