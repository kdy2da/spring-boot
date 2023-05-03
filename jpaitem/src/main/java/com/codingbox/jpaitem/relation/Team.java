package com.codingbox.jpaitem.relation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "TEAM")
public class Team {
	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	
	/*
	 * @OneToMany 	: Team 에서 Member로 가는건 '1 대 다'이기 때문
	 * mappedBy		: 주인이 아닌쪽
	 * 				  읽기만 가능
	 * 				  값을 넣어봐야 아무일도 벌어지지 않는다. 대신 조회 가능
	 */
	@OneToMany(mappedBy = "team")
	private List<Member> member
		= new ArrayList<>();
	
	public void addMember(Member member) {
		member.setTeam(this);
		this.member.add(member);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", member=" + member + "]";
	}
	
	
}
