package com.codingbox.jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {
	
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
			
			// 타입 정보가 명확할 때
			TypedQuery<Member> query
				= em.createQuery("select m from Member m", Member.class);
			
			// 여러 건일 경우 collection 이 반환 된다.
			List<Member> resultList = query.getResultList();
			
			for( Member m : resultList) {
				System.out.println("m = " + m);
			}
			
			// 결과값이 하나일 경우( 값이 있다고 보장 될 때만 쓴다! )
			Member result = query.getSingleResult();
			
			// 타입 정보가 String.class로 반환타입이 명확할 때 사용
			TypedQuery<String> query2
				= em.createQuery("select m.username from Member m", String.class);
			
			// List<String> resultString = query2.getResultList();			
			
			// m.username(String), m.age(int) : 이렇게 반환타입이 명확하지 않을 때 사용
			Query query3 = em. createQuery("select m.username, m.age from Member m");
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}





















