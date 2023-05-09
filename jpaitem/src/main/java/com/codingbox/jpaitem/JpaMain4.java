package com.codingbox.jpaitem;


import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.jpaitem.embedded.Address;
import com.codingbox.jpaitem.embedded.Member;
import com.codingbox.jpaitem.embedded.Period;




public class JpaMain4 {
	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
	
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Address addr = new Address("서울", "역삼", "123");
			
			Member member = new Member();
			member.setUsername("user1");
			member.setAddress(addr);
			em.persist(member);
			
			Address copyAddr 
			= new Address(addr.getCity(), addr.getStreet(), addr.getZipcode());
			
			Member member2 = new Member();
			member2.setUsername("user2");
			member2.setAddress(copyAddr);
			em.persist(member2);
			
			member.getAddress().setCity("newCity");
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
