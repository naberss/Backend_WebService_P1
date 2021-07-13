package com.nabers.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.repositories.OrderItemRepository;
import com.nabers.spring.services.Exceptions.DatabaseException;
import com.nabers.spring.services.Exceptions.ResourceNotFoundException;

@Service
@Profile(value = { "dev", "test", "prod" })
public class OrderItemService {

	@Autowired
	public OrderItemRepository orderItemRepository;

	public void Insert(OrderItem orderitem) {
		orderItemRepository.save(orderitem);
	}

	public Optional<OrderItem> findById(Integer product_id, Integer order_id) {
		return Optional.of(
				orderItemRepository.findbyId(product_id, order_id).orElseThrow(() -> new ResourceNotFoundException()));
	}

	public Iterable<OrderItem> findByProduct(int id) {

		List<OrderItem> orderItems = (List<OrderItem>) orderItemRepository.findbyProduct(id);

		if (orderItems.size() != 0) {
			return orderItems;
		}
		throw new ResourceNotFoundException(id);

	}

	public Iterable<OrderItem> findByOrder(int id) {

		List<OrderItem> orderItems = (List<OrderItem>) orderItemRepository.findbyOrder(id);

		if (orderItems.size() != 0) {
			return orderItems;
		}
		throw new ResourceNotFoundException(id);

	}

	public Iterable<OrderItem> findAll() {

		List<OrderItem> orderItems = (List<OrderItem>) orderItemRepository.findAll();
		;

		if (orderItems.size() != 0) {
			return orderItems;
		}
		throw new ResourceNotFoundException();

	}

	public OrderItem update(Integer product_id, Integer order_id, OrderItem newOrderItem) {
		try {
			OrderItem orderItem = orderItemRepository.findbyId(product_id, order_id).orElse(null);
			updateData(orderItem, newOrderItem);
			orderItemRepository.save(orderItem);
			return orderItem;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException();
		}
	}

	public void updateData(OrderItem oldOrderItem, OrderItem newOrderItem) {
		oldOrderItem.setQuantity(newOrderItem.getQuantity());
	}

	public void deleteById(Integer product_id, Integer order_id) {
		try {
			orderItemRepository.deleteById(product_id, order_id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (EmptyResultDataAccessException f) {
			throw new ResourceNotFoundException();

		}
	}

}
