package com.codingbox.springshop.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingbox.springshop.domain.Item;
import com.codingbox.springshop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;

	
	// 메서드 이름 	: void, saveItem()
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	// 메서드		: findItems()
	// return	: List
	public List<Item> findItems() {
		return itemRepository.findAll();
	}
	
	
}
















