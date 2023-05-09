package com.codingbox.jpql;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain5 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.setTeam(team);
			em.persist(member);
			
			em.flush();
			em.clear();
			
			// 조인, innert 생략가능
			String jpql = "select m from Member m inner join m.team t";
			List<Member> resultList = em.createQuery(jpql, Member.class)
										.getResultList();
			
			// left outer join , outer는 생략 가능
			String jpql2 = "select m from Member m left outer join m.team t";
			List<Member> resultList2 = em.createQuery(jpql2, Member.class)
										 .getResultList();
			
			// 세타조인(막 조인)
			String jpql3 = "select m from Member m, Team t where m.username = t.name";
			List<Member> resultList3 =  em.createQuery(jpql3, Member.class)
										  .getResultList();
			
			tx.commit();
			
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}





















