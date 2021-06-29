package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.Order;
import com.nabers.spring.repositories.OrderRepository;

@Service
@Profile(value = "test")
public class OrderService {

	@Autowired
	public OrderRepository orderRepository;

	public void InsertUpdate(Order user) {
		orderRepository.save(user);
	}

	public Optional<Order> findById(int id) {
		return orderRepository.findById(id);
	}

	public void deleteById(int id) {
		orderRepository.deleteById(id);
	}	

}
