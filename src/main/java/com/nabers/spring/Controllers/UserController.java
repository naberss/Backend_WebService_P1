package com.nabers.spring.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nabers.spring.entities.User;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/user")
public class UserController {

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public User getUser() {
		return new User(1, "lucas", "lucas-berto@hotmail.com", "+27998841869", "03031998");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo2")
	ResponseEntity<User> getUser2() {
		User u = new User(1, "lucas", "lucas-berto@hotmail.com", "+27998841869", "03031998");
		return ResponseEntity.unprocessableEntity().body(u);
	}
}
