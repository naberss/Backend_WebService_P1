package com.nabers.spring.controllers;

import java.time.Instant;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nabers.spring.entities.Order;
import com.nabers.spring.entities.User;
import com.nabers.spring.services.OrderService;
import com.nabers.spring.services.UserService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	ResponseEntity<Order> getOrder() {
		User u = new User(1, "lucas", "lucas-berto@hotmail.com", "27998841869", "03031998");		
		Order o1 = new Order(1, Instant.parse("2019-06-20T19:53:07Z"), u);
		userService.InsertUpdate(u);
		orderService.InsertUpdate(o1);
		return ResponseEntity.unprocessableEntity().body(o1);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertUpdate")
	public @ResponseBody ResponseEntity<Order> insertUpdate(@Valid Order o1) {
		orderService.InsertUpdate(o1);
		return ResponseEntity.accepted().body(o1);

	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo/{id}")
	public ResponseEntity<Optional<Order>> showInfo(@PathVariable(name = "id") int id) {
		Optional<Order> order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}

}
