package com.codingbox.springshop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.codingbox.springshop.domain.Order;
import com.codingbox.springshop.domain.OrderStatus;
import com.codingbox.springshop.domain.QMember;
import com.codingbox.springshop.domain.QOrder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	@Autowired
	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	/*
	 * queryDSL
	 */
	public List<Order> findAll(OrderSearch orderSearch) {
		// queryDSL -> QClass 세팅(복잡)
		JPAQueryFactory query = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		
		return query.select(order)
				.from(order)
				.join(order.member, member) // member : member 의 alias
//				.where(order.status.eq(orderSearch.getOrderStatus()), member.name.like(orderSearch.getMemberName()))
				.where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
				.limit(1000)
				.fetch()
				;
	}
	private BooleanExpression statusEq(OrderStatus orderStatus) {
		if( orderStatus == null ) {
			return null;
		}
		return QOrder.order.status.eq(orderStatus);
		
	}
	private BooleanExpression nameLike(String memberName) {
		if( memberName == null || memberName.equals("")) {
			return null;
		}
//		return QMember.member.name.like(memberName);
		return QMember.member.name.contains(memberName);
	}
	


	/*
	 * jpql
	 */
//  public List<Order> findAll(OrderSearch orderSearch){
//     String jpql = "select o from Order o join o.member m";
//     boolean isFirstCondition = true;
//     
//     //주문 상태 검색 
//     if (orderSearch.getOrderStatus() != null) { 
//        if (isFirstCondition) { 
//           jpql += " where"; 
//           isFirstCondition = false; 
//        } else { 
//           jpql += " and"; 
//        } 
//           jpql += " o.status = :status"; 
//     }
//     
//     //회원 이름 검색 
//     if (StringUtils.hasText(orderSearch.getMemberName())) { 
//        if (isFirstCondition) { 
//           jpql += " where"; 
//           isFirstCondition = false; 
//        } else { 
//           jpql += " and"; 
//        } 
//        jpql += " m.name like :name"; 
//     }
//     
//     TypedQuery<Order> query = em.createQuery(jpql, Order.class)  
//                          .setMaxResults(1000); //최대 1000건 
//     if (orderSearch.getOrderStatus() != null) { 
//        query = query.setParameter("status", orderSearch.getOrderStatus());  
//     } 
//     if (StringUtils.hasText(orderSearch.getMemberName())) {
//        query = query.setParameter("name", orderSearch.getMemberName());  
//     } 
//     
//     return query.getResultList(); 
//  }

}
