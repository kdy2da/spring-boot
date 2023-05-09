package com.codingbox.jpatest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.codingbox.jpatest.domain.Member;
import com.codingbox.jpatest.domain.Orders;

/*
 * 2-1. testMain1 수행시 com.codingbox.jpatest.domain 에 있는 Member, Orders 엔티티 활성화
 * (com.codingbox.jpaitem.embedded 에 있는 Member, Orders 엔티티 비활성화(주석) )
 */
public class testMain1 {
	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
	
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setName("member");
			member.setCity("서울");
			member.setStreet("거꾸로해도 역삼역");
			member.setZipcode("123");
			em.persist(member);
			
			Orders order1 = new Orders();
			order1.setOrderDate(LocalDateTime.now());
			order1.setStatus("접수0");
			order1.changeMember(member);
			em.persist(order1);
			
			Orders order2 = new Orders();
			order2.setOrderDate(LocalDateTime.now());
			order2.setStatus("접수1");
			order2.changeMember(member);
			em.persist(order2);
			
			Orders order3 = new Orders();
			order3.setOrderDate(LocalDateTime.now());
			order3.setStatus("접수2");
			order3.changeMember(member);
			em.persist(order3);
			
			Orders order4 = new Orders();
			order4.setOrderDate(LocalDateTime.now());
			order4.setStatus("접수3");
			order4.changeMember(member);
			em.persist(order4);
			
			Orders order5 = new Orders();
			order5.setOrderDate(LocalDateTime.now());
			order5.setStatus("접수4");
			order5.changeMember(member);
			em.persist(order5);
			
			Orders order6 = new Orders();
			order6.setOrderDate(LocalDateTime.now());
			order6.setStatus("접수5");
			order6.changeMember(member);
			em.persist(order6);
			
			Orders order7 = new Orders();
			order7.setOrderDate(LocalDateTime.now());
			order7.setStatus("접수6");
			order7.changeMember(member);
			em.persist(order7);
			
			Orders order8 = new Orders();
			order8.setOrderDate(LocalDateTime.now());
			order8.setStatus("접수7");
			order8.changeMember(member);
			em.persist(order8);
			
			Orders order9 = new Orders();
			order9.setOrderDate(LocalDateTime.now());
			order9.setStatus("접수8");
			order9.changeMember(member);
			em.persist(order9);
			
			Orders order10 = new Orders();
			order10.setOrderDate(LocalDateTime.now());
			order10.setStatus("접수9");
			order10.changeMember(member);
			em.persist(order10);
			
			em.flush();
			em.clear();
			
			Member findMember = em.find(Member.class, member.getId());
			String str1 = findMember.toString();
			List<Orders> orders = findMember.getOrders();
			List<String> strList = new ArrayList<>();
			for(Orders order : orders) {
				strList.add(order.toString());
			}
			
			System.out.println(str1);
			for(String str: strList) {
				System.out.println(str);
			}
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
