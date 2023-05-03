package com.codingbox.web.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codingbox.web.interceptor.LoginCheckInterceptor;
import com.codingbox.web.interceptor.LoginInterceptor;


@Configuration
public class WebConfig2 implements WebMvcConfigurer {
	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/util/pattern/PathPattern.html
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.order(1)
			.addPathPatterns("/**")	// 모든 경로 전체에서 수행
			.excludePathPatterns("/basic/**");	// 해당 요청 URI 이는 이 인터셉터를 거치지 않는다. 그리고 그 다음 우선순위 인터셉터로 넘어간다.
		// 위 인터셉터와 독립적인 인터셉터이다. 다만 우선순위가 정해져 있다.
		registry.addInterceptor(new LoginCheckInterceptor())
			.order(2)
			.addPathPatterns("/**")	// 모든 경로 전체에서 수행
			.excludePathPatterns("/","/members/add", "/login", "/logout", "/css/**"); // 제외할 경우
	}
	
}
