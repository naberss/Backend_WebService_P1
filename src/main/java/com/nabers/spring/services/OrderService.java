package com.nabers.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.Order;
import com.nabers.spring.repositories.OrderRepository;
import com.nabers.spring.services.Exceptions.DatabaseException;
import com.nabers.spring.services.Exceptions.ResourceNotFoundException;

@Service
@Profile(value = { "dev", "test", "prod" })
public class OrderService {

	@Autowired
	public OrderRepository orderRepository;

	public void Insert(Order order) {
		orderRepository.save(order);
	}

	public Optional<Order> findById(int id) {
		return Optional.of(orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	public Order findByIdAux(int id) {
		return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Iterable<Order> findAll() {
		List<Order> orders = orderRepository.findAll();

		if (orders.size() != 0) {
			return orders;
		}
		throw new ResourceNotFoundException();
	}

	public Order update(Integer id, Order newOrder) {

		try {

			Order order = orderRepository.findById(id).orElse(null);
			updateData(order, newOrder);
			orderRepository.save(order);
			return order;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException();
		}
	}

	public void updateData(Order oldOrder, Order newOrder) {
		oldOrder.setPayment(newOrder.getPayment());
		oldOrder.setStatus(newOrder.getStatus());

	}

	public void deleteById(int id) {
		try {
			orderRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (EmptyResultDataAccessException f) {
			throw new ResourceNotFoundException(id);

		}

	}

}
