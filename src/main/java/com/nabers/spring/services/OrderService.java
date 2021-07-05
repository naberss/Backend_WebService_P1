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

	public void Insert(Order order) {
		orderRepository.save(order);
	}

	public Optional<Order> findById(int id) {
		return orderRepository.findById(id);
	}
	
	public Order findByIdAux(int id) {
		return orderRepository.findById(id).orElse(null);
	}

	public Iterable<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order update(Integer id, Order newOrder) {
		Order order = orderRepository.findById(id).orElse(null);
		updateData(order, newOrder);
		orderRepository.save(order);
		return order;
	}

	public void updateData(Order oldOrder, Order newOrder) {
		oldOrder.setPayment(newOrder.getPayment());
		oldOrder.setStatus(newOrder.getStatus());

	}

	public void deleteById(int id) {
		orderRepository.deleteById(id);
	}

}
