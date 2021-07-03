package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.repositories.OrderItemRepository;

@Service
@Profile(value = "test")
public class OrderItemService {

	@Autowired
	public OrderItemRepository orderItemRepository;

	public void InsertUpdate(OrderItem orderitem) {
		orderItemRepository.save(orderitem);
	}

	public Optional<OrderItem> findById(int id) {
		return orderItemRepository.findById(id);
	}

	public void deleteById(int id) {
		orderItemRepository.deleteById(id);
	}



}
