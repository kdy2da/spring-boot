package com.codingbox.jpql;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain2 {
	
	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);
			
			// 파라미터 바인딩 - 이름기준
//			TypedQuery<Member> query
//				= em.createQuery("select m from Member m where m.username = :username", Member.class);
//			query.setParameter("username", "member1");
//			Member result = query.getSingleResult();
			
			Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
					.setParameter("username", "member1")
					.getSingleResult();
			
			System.out.println("result = " + result);
			System.out.println("result = " + result.getUsername());
			
			// 파라미터 바인딩 - 위치기반(쓰지말것)
			Member result2 
				= em.createQuery("select m from Member m where m.username = ?1",Member.class)
					.setParameter(1, "member1")
					.getSingleResult();
			
			System.out.println("result2 = " + result2.getUsername());
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}





















