package com.codingbox.core3.domain.web.basic;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestParamController {

	/*
	 *  반환 타입이 없고, 응답(response)에 값을 집어넣으면,
	 *  view 조회 x
	 */
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println("username : " + username);
		System.out.println("age : " + age);
		response.getWriter().write("ok");
	}
	/*
		@RequestParam("name") : 기본 name이라는 key값을 파싱한다. 추가적으로 옵션을 넣어 줄 수 있다.
		- required : 파라미터 값 필수 여부,
				* true -> 필수(default)
				* false-> 필수 아님
		- defaultValue : 파라미터 값이 없을 경우, 기본으로 들어갈 값
	 * @RequestParam
	 *  - 파라미터 이름으로 바인딩
	 *  - @RequestParam("username") String memberName
	 *     = String memberName = request.getParameter("username");
	 * @ResponseBody
	 *  - view 조회를 무시하고,
	 *    HTTP message body 에 직접 해당 내용 입력
	 */
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2
		(@RequestParam("username") String memberName
				,@RequestParam("age") int memberAge) {
		System.out.println("username : " + memberName);
		System.out.println("age		 : " + memberAge);
		
		return "ok";
	}
	
	/*
	 * @RequestParam 사용
	 *  - HTTP 파라미터 이름이 method 파라미터 변수 이름과 같으면
	 *    @RequestParam("XXX") 생략 가능
	 */
	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3
		(@RequestParam String username
				,@RequestParam Integer age) {
		System.out.println("username : " + username);
		System.out.println("age		 : " + age);
		
		return "ok";
	}
	/*
	 * @RequestParam
	 *  - String, int 등의 단순 타입이면 @RequestParam 도 생략 가능
	 */
	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4
		(String username
				,Integer age) {
		System.out.println("username : " + username);
		System.out.println("age		 : " + age);
		
		return "ok";
	}
	
	/*
	 * @RequestParam  (HTTP 파라미터 필수 여부, default : true)
	 *  - required = true : 반드시 파라미터 값이 들어와야 한다.
	 *  - required = false : 파라미터 값이 들어오지 않아도 된다.
	 *  
	 *  int age -> Integer age
	 *   - null 을 int 에 입력하는 것은 불가능,
	 *   	따라서 Integer 로 변경해야 함.
	 */
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired
		(@RequestParam(required=true) String username
				,@RequestParam(required=false) Integer age) {
		System.out.println("username : " + username);
		System.out.println("age		 : " + age);
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault
		(@RequestParam(required=true, defaultValue="guest") String username
				,@RequestParam(required=false, defaultValue="-1") Integer age) {
		System.out.println("username : " + username);
		System.out.println("age		 : " + age);
		
		return "ok";
	}
	/*
	 * @RequestParam
	 *  - Map<Key, Value>
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
		
		System.out.println("username : " + paramMap.get("username"));
		System.out.println("age : " + paramMap.get("age"));
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@RequestParam String username,
			@RequestParam Integer age) {
		HelloData helloData = new HelloData();
		helloData.setUsername(username);
		helloData.setAge(age);
		
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		
		return "ok";
	}
	
//	@ResponseBody
//	@RequestMapping("/model-attribute-v1_1")
//	public String modelAttributeV1_1(@RequestParam HelloData helloData) {
//		
//		System.out.println("username : " + helloData.getUsername());
//		System.out.println("age : " + helloData.getAge());
//		
//		return "ok";
//	}
	
	
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(@ModelAttribute HelloData helloData) {
		
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		System.out.println(helloData.toString());
		return "ok";
	}
	
	/*
	 * @ModelAttribute 생략 가능
	 *  - String, int 같은 단순 타입 	: @RequestParam
	 *  - 사용자 정의 객체				: @ModelAttribute
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v3")
	public String modelAttributeV3(HelloData helloData) {
		
		System.out.println("username : " + helloData.getUsername());
		System.out.println("age : " + helloData.getAge());
		System.out.println(helloData.toString());
		return "ok";
	}
}
