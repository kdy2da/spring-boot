package com.codingbox.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	/*
	 * - 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
	 * - JPA 의 모든 데이터 변경은 트랜잭션 안에서 실행
	 */

	public static void main(String[] args) {
		EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		//transaction : 데이터베이스의 상태를 변화시키기 위해 수행하는 작업 단위
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// JPQL
			// JPA 는 쿼리를 짤때 table을 대상으로 쿼리를 짜지 않고,
			// Member 객체를 통해서 쿼리를 짠다고 보면 된다.
			List<Member> result = em.createQuery("select m from Member as m", Member.class)
					.setFirstResult(5) // 5번부터
					.setMaxResults(10) // 10개 가지고와
					.getResultList();
			
			for( Member member : result ) {
				System.out.println("member.name = " + member.getName());
			}
			
			
//			Member member = new Member();
//			// 추가
//			member.setId(1L);
//			member.setName("UserA");
//			em.persist(member);
//			Member member2 = new Member();
//			// 추가
//			member2.setId(2L);
//			member2.setName("UserB");
//			em.persist(member2);
//			// 회원조회
//			Member findMember = em.find(Member.class, 1L);
//			System.out.println("findMember.id : " + findMember.getId());
//			System.out.println("findMember.name : " + findMember.getName());
//			
//			// 회원수정
//			findMember.setName("Hello");
//			
//			// 회원삭제
//			em.remove(findMember);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		
		
		
	}

}
