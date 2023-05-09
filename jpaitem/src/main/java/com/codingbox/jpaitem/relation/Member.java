package com.codingbox.jpaitem.relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
@Table(name = "MEMBER")
public class Member {
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	@Column(name = "USERNAME")
	private String name;
	
//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
	/*
	 * 1대다의 개념을 갹체에게 알려줘야한다.
	 * DB를 기준으로 1대다의 개념을 줘야한다.
	 * @ManyToOne : 여기선 Team이 One이다.
	 * @JoinColumn : 관계 컬럼을 적어준다.
	 * 
	 * 외래키가 있는 객체가 주인이다.
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	// lombok에서 자동으로 setter 생성을 막아준다.
	@Setter(value = AccessLevel.NONE)	
	private Team team;

	/*
	 * 일반적인 setter의 형태(자바에서의 관례 형태를 벗어났다)가 아니면
	 * 이름을 바꿔준다. 그럼 추후 소스코드를 봤을 때 단순 setter 작업이 아닌
	 * 중요한 작업을 진행하는지를 파악할 수 있다.
	 */
	public void changeTeam(Team team) {
		this.team = team;
		// this : 나 자신의 인터스턴스를 넣어준다.
		team.getMember().add(this);
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", team=" + team + "]";
	}
	
	
}
