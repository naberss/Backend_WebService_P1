package com.nabers.spring.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nabers.spring.entities.User;
import com.nabers.spring.services.UserService;

@RestController
@Profile(value = {"dev","test","prod"})
@RequestMapping(method = RequestMethod.GET, path = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public @ResponseBody ResponseEntity<User> insert(@Valid @RequestBody User user) {
		userService.Insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyid/{id}")
	public ResponseEntity<Optional<User>> findById(@PathVariable(name = "id") int id) {
		Optional<User> user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyname/{name}")
	public ResponseEntity<Iterable<User>> findByName(@PathVariable(name = "name") String name) {
		Iterable<User> users = userService.findByName(name);
		return ResponseEntity.ok().body(users);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findall")
	public ResponseEntity<Iterable<User>> findAll() {
		Iterable<User> allusers = userService.findAll();
		return ResponseEntity.ok().body(allusers);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public ResponseEntity<User> Update(@PathVariable(name = "id") int id, @RequestBody User user) {
		User obj = userService.update(id, user);
		return ResponseEntity.accepted().body(obj);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public ResponseEntity<User> deleteById(@PathVariable int id) {
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
