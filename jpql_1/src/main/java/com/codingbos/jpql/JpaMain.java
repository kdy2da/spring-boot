package com.codingbos.jpql;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.print.attribute.standard.Media;

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
			
			// 타입 정보가 명확할때
			TypedQuery<Member> query = 
				em.createQuery("select m from Member m", Member.class);
			// 여러 건일 경우 collection이 반환 된다.
			List<Member> resultList = query.getResultList();
			
			for( Member member2 : resultList ) {
				System.out.println("member2 = " + member2);
			}
			
			// 결과값이 하나일 경우
			Member result = query.getSingleResult();
			
			
			// 타입정보가 String.class로 반환타입이 명확할때 사용
			TypedQuery<String> query2 = 
				em.createQuery("select m.username from Member m", String.class);
			
			
			
			// m.username(String), m.age(int) : 이렇게 반환타입이 명확하지 않을 때 사용
			Query query3 = 
				em.createQuery("select m.username, m.age from Member m");
			
			
			
			
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}





















