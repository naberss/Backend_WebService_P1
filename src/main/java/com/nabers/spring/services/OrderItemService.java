package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.repositories.OrderItemRepository;

@Service
@Profile(value = "dev")
public class OrderItemService {

	@Autowired
	public OrderItemRepository orderItemRepository;

	public void Insert(OrderItem orderitem) {
		orderItemRepository.save(orderitem);
	}

	public Optional<OrderItem> findById(Integer product_id, Integer order_id) {
		return orderItemRepository.findbyId(product_id, order_id);
	}

	public Iterable<OrderItem> findByProduct(int id) {
		return orderItemRepository.findbyProduct(id);
	}

	public Iterable<OrderItem> findByOrder(int id) {
		return orderItemRepository.findbyOrder(id);
	}

	public Iterable<OrderItem> findAll() {
		return orderItemRepository.findAll();
	}

	public OrderItem update(Integer product_id, Integer order_id, OrderItem newOrderItem) {
		OrderItem orderItem = orderItemRepository.findbyId(product_id, order_id).orElse(null);
		updateData(orderItem, newOrderItem);
		orderItemRepository.save(orderItem);
		return orderItem;
	}

	public void updateData(OrderItem oldOrderItem, OrderItem newOrderItem) {
		oldOrderItem.setQuantity(newOrderItem.getQuantity());
	}

	public void deleteById(Integer product_id, Integer order_id) {
		orderItemRepository.deleteById(product_id,order_id);
	}

}
