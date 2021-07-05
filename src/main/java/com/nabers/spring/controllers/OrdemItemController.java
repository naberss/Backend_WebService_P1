package com.nabers.spring.controllers;

import java.time.Instant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nabers.spring.entities.Order;
import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.entities.Product;
import com.nabers.spring.entities.User;
import com.nabers.spring.entities.enums.OrderStatus;
import com.nabers.spring.services.OrderItemService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/orderitem")
public class OrdemItemController {

	@Autowired
	OrderItemService ordemItemService;

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public OrderItem getUser() {
		User u = new User(1, "lucas", "lucas-berto@hotmail.com", "27998841869", "03031998");
		Order o1 = new Order(1, Instant.parse("2019-06-20T19:53:07Z"), u, OrderStatus.DELIVERED);
		Product p1 = new Product(1, "SAMSUNG BOOK", "QUITE A NICE COMPUTER", 1500.0);
		return new OrderItem(p1, o1, 1, p1.getPrice());
	}
   
	@RequestMapping(method = RequestMethod.GET, path = "/getinfo2")
	ResponseEntity<OrderItem> getUser2() {

		User u = new User(1, "lucas", "lucas-berto@hotmail.com", "27998841869", "03031998");
		Order o1 = new Order(1, Instant.parse("2019-06-20T19:53:07Z"), u, OrderStatus.DELIVERED);
		Product p1 = new Product(1, "SAMSUNG BOOK", "QUITE A NICE COMPUTER", 1500.0);
		OrderItem orderitem = new OrderItem(p1, o1, 1, p1.getPrice());
		ordemItemService.InsertUpdate(orderitem);

		return ResponseEntity.unprocessableEntity().body(orderitem);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertUpdate")
	public @ResponseBody ResponseEntity<OrderItem> insertUpdate(@Valid OrderItem orderitem) {
		ordemItemService.InsertUpdate(orderitem);
		return ResponseEntity.accepted().body(orderitem);

	}
}
