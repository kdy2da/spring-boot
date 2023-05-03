package com.codingbox.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.jpaitem.relation.Member;
import com.codingbox.jpaitem.relation.Team;



public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
	
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			// 저장
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
			member.setTeam(team);
			em.persist(member);
			
			// 강제로 db 쿼리를 보고 싶을 때
			em.flush();
			em.clear();
			
			// 조회
			Member findMember = em.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();
			System.out.println("findTeamName : " + findTeam.getName());
			
			// 양방향 매핑
			Member findSideMember = em.find(Member.class, member.getId());
			List<Member> members = findSideMember.getTeam().getMember();
			
			for(Member m : members) {
				System.out.println("result = " + m.getName());
			}
			
			
//			Team team = new Team();
//			team.setName("TeamA");
//			em.persist(team);	// 영속상태가 되면 pk의 값이 세팅이 된 후 영속상태가 된다.
//			
//			Member member = new Member();
//			member.setName("member1");
//			member.setTeamId(team.getId());
//			em.persist(member);
//			
//			
//			// select
//			// 특정 멤버가 속해있는 팀의 이름을 알고 싶을 때
//			Member findMember = em.find(Member.class, member.getId());
//			Long findTeamId = findMember.getTeamId();
//			Team findTeam = em.find(Team.class, findTeamId);
//			System.out.println("findTeam : " + findTeam.getName());
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
