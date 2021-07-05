package com.nabers.spring.controllers;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nabers.spring.entities.Order;
import com.nabers.spring.entities.enums.OrderStatus;
import com.nabers.spring.services.OrderService;
import com.nabers.spring.services.UserService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public @ResponseBody ResponseEntity<Order> insert(@RequestParam(name = "id") Integer id,
			                                          @RequestParam(name = "moment") Instant moment,
			                                          @RequestParam(name = "user_id") Integer user_id,
			                                          @RequestParam(name = "status") String status) {
		Order order = new Order(id, moment, userService.findByIdAux(id), OrderStatus.valueOf(status));
		orderService.Insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(order);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyid/{id}")
	public ResponseEntity<Optional<Order>> showInfo(@PathVariable(name = "id") int id) {
		Optional<Order> order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findall")
	public @ResponseBody ResponseEntity<Iterable<Order>> findAll() {
		Iterable<Order> allOrders = orderService.findAll();
		return ResponseEntity.ok().body(allOrders);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public @ResponseBody ResponseEntity<Order> update(@PathVariable(name = "id") Integer id,
			                                          @RequestBody Order newOrder) {
		newOrder.getPayment().setOrder(orderService.findByIdAux(id));
		Order order = orderService.update(id, newOrder);
		return ResponseEntity.accepted().body(order);

	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public @ResponseBody ResponseEntity<Order> update(@PathVariable(name = "id") Integer id) {
		orderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
