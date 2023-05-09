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

public class JpaMain3 {
	
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
			
			em.flush();
			em.clear();
			
			// 엔티티 프로젝션
			// m은 엔티티
			List<Member> result = em.createQuery("select m from Member m", Member.class)
									.getResultList();
			
			// 이 영역이 update가 되면, 영속성 컨텍스트에서 관리가 되고,
			// update가 안되면 관리가 안된다는 뜻
			Member findMember = result.get(0);
			findMember.setAge(20);
			
			// 엔티티 프로턱션
			/*
			 * jpql입장에선 일반 select문이 나가지만, 실제 sql 입장에서는 join결과가 발생한다. 
			 * 권장하지 않음
			 */
//			List<Team> result2 = em.createQuery("select m.team from Member m", Team.class)
//								   .getResultList();
			
			// join문
			List<Team> result2 = em.createQuery("select t from Member m join m.team t", Team.class)
								   .getResultList();
			
			// 임베디드 타입 프로젝션
			em.createQuery("select o.address from Order o", Address.class)
			  .getResultList();
			
			// 에러발생
			// from Address 로 가져올 수 없다. o.address가 소속되어있는 엔티티에서 가지고 와야 한다.
//			em.createQuery("select o.address from Address o", Address.class)
//			  .getResultList();
			
			// 여러 값 조회
			///////////////////////////// 1단계
			List resultList = em.createQuery("select m.username, m.age from Member m")
								.getResultList();
			
			// 타입을 지정하지 못하니까 object 받아오기
			Object o = resultList.get(0);
			Object[] result3 = (Object[])o;
			System.out.println("username = " + result3[0]);
			System.out.println("age = " + result3[1]);
			
			//////////////////////////// 2단계
			List<Object[]> resultList2 = em.createQuery("select m.username, m.age from Member m")
									   	  .getResultList();
			
			Object[] result4 = resultList2.get(0);
			System.out.println("username = " + result4[0]);
			System.out.println("age = " + result4[1]);
			
			//////////////////////////// 3단계 - 제일 권장
			
			// 마치 생성자를 호출한 듯한 문법
			List<MemberDTO> result5 
				= em.createQuery("select new com.codingbos.jpql.MemberDTO(m.username, m.age) from Member m")
					.getResultList();
			
			MemberDTO memberDTO = result5.get(0);
			System.out.println("memberDTO : " + memberDTO.getUsername());
			System.out.println("memberDTO : " + memberDTO.getAge());
			
			
			
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}






















