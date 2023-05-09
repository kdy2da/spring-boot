package com.codingbox.springshop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codingbox.springshop.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	@Autowired
	private final EntityManager em;
	
	public void save(Item item) {
		
//		// 처음에 item이 없으면 id가 null 값이기 때문이다.
//		if(item.getId() == null) {
//			// 신규 등록
			em.persist(item);
//		} else {
//			// update
//			em.merge(item);
//		}
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i",Item.class).getResultList();
	}
//	item 하나 조회
//	메서드 명 : findOne
	public Item findOne(Long itemId) {
		return em.createQuery("select i from Item i where i.id = :itemId",Item.class)
				.setParameter("itemId", itemId)
				.getSingleResult();
	}

//	public void update(Item updateItem) {
//		Item item = em.find(Item.class, updateItem.getId());
//		item.setName(updateItem.getName());
//		item.setPrice(updateItem.getPrice());
//		item.setStockQuantity(updateItem.getStockQuantity());
//	}
}
