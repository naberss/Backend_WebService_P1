package com.nabers.spring.controllers;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nabers.spring.entities.Order;
import com.nabers.spring.entities.User;
import com.nabers.spring.entities.enums.OrderStatus;
import com.nabers.spring.services.OrderService;
import com.nabers.spring.services.UserService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public User getUser() {
		return new User(1, "lucas", "lucas-berto@hotmail.com", "27998841869", "03031998");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo2")
	ResponseEntity<User> getUser2() {
		User u = new User(1, "lucas", "lucas-berto@hotmail.com", "27998841869", "03031998");
		userService.InsertUpdate(u);
		Order o = new Order(1, Instant.parse("2019-06-20T19:53:07Z"), u, OrderStatus.DELIVERED);
		orderService.InsertUpdate(o);
		Order o1 = new Order(2, Instant.parse("2018-06-20T19:53:07Z"), u, OrderStatus.SHIPPED);
		orderService.InsertUpdate(o1);
		return ResponseEntity.unprocessableEntity().body(u);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo/{id}")
	public Optional<User> findById(@PathVariable(name = "id") int id) {
		Optional<User> user = userService.findById(id);
		return user;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertUpdate")
	public @ResponseBody ResponseEntity<User> insertUpdate(@Valid @RequestBody User u) {
		userService.InsertUpdate(u);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();

		return ResponseEntity.created(uri).body(u);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public ResponseEntity<User> deleteById(@PathVariable int id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public ResponseEntity<User> Update(@PathVariable(name = "id") int id, @RequestBody User user) {
		User obj = userService.update(id, user);
		return ResponseEntity.ok().body(obj);
	}

}
