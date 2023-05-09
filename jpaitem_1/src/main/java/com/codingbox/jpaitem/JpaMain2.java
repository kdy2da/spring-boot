package com.codingbox.jpaitem;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.print.attribute.standard.Media;

import com.codingbox.jpaitem.relation.Member;
import com.codingbox.jpaitem.relation.Team;

public class JpaMain2 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team team = new Team();
			team.setName("TeamB");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member2");
//			member.setTeam(team);
			//member.changeTeam(team);	// 1안 : member를 기준으로 team을 넣는다.
			em.persist(member);
			
			team.addMember(member);		// 2안 : team을 기준으로 member를 넣는다.
			
			
			// 강제로 db쿼리를 보고 싶을때
//			em.flush();
//			em.clear();
			
			// 양방향 매핑시에는 양쪽에 값을 모두 입력해 주어야 한다.
			// DB를 다시 다녀오지않고 객체 상태로만 사용 할 수 있다.
//			team.getMember().add(member);
			
			// 오너가 아니여도 양방향 매핑에서는 select 가능
			System.out.println("===============================");
			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMember();
			
			System.out.println("members = " + findTeam);
			
			for( Member m : members ) {
				System.out.println("result = " + m.getName());
			}
			System.out.println("===============================");
			
			/*
			 * - insert 문만 실행되고, select 문은 실행되지 않는다.
			 * - 이유	: team이 영속성 컨텍스트에 들어가있는데(1차 캐시), 현재 변경된 것을
			 * 			감지하지 못한 상태(flush가 호출되지 않은 상태)에서 검색을 하니
			 * 			select가 되지 않은 것.
			 * 			즉, 1차 캐시에서 조회된 것이 그대로 나오게 된다.
			 * 			1차 캐시에 담긴 내용이 그대로 조회된다.
			 */
			
		
			
			
			/*
			// 저장
			Member member = new Member();
			member.setName("member1");
			em.persist(member);
			
			Team team = new Team();
			team.setName("TeamA");
			team.getMember().add(member);
			em.persist(team);
			*/
		
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}





















