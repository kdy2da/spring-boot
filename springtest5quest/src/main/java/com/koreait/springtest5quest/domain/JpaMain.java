package com.koreait.springtest5quest.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setName("member");
			//2-2
//			member.setCity("서울");
//			member.setStreet("거꾸로해도 역삼역");
//			member.setZipcode("123");
			//2-3
			member.setAddress(new Address("서울", "거꾸로해도 역삼역", "123"));
			em.persist(member);
			
			for(int i = 0; i<10; i++) {
				Order order = new Order();
				order.setOrderDate(LocalDateTime.now());
				order.setStatus("접수" + i);
				order.changeMember(member);
				em.persist(order);
			}
			
			em.flush();
			em.clear();
			
			System.out.println("=====================");
			Member findMember = em.find(Member.class, member.getId());
			List<Order> orders = findMember.getOrders();
			
			System.out.println("Member = " + orders.get(0).getMember().toString());
			
			for(Order m : orders) {
				System.out.println("Order = " + m.toString());
			}
			System.out.println("=====================");
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}










