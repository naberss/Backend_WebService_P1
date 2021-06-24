package com.nabers.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nabers.spring.entities.User;
import com.nabers.spring.services.UserService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public User getUser() {
		return new User(1, "lucas", "lucas-berto@hotmail.com", "+27998841869", "03031998");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo2")
	ResponseEntity<User> getUser2() {
		User u = new User(1, "lucas", "lucas-berto@hotmail.com", "+27998841869", "03031998");
		return ResponseEntity.unprocessableEntity().body(u);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertUpdate")
	public @ResponseBody ResponseEntity<User> insertUpdate(@Valid User u) {
		userService.InsertUpdate(u);
		return ResponseEntity.accepted().body(u);

	}
}
