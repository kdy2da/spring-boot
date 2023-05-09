package com.codingbox.jpaitem.embedded;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Period {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// 파라미터 있는 생성자를 선언 시,
	// 기본 생성자 필수적으로 선언 해줘야 한다.
	public Period() {}
}
