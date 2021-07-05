package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.User;
import com.nabers.spring.repositories.UserRepository;

@Service
@Profile(value = "test")
public class UserService {

	@Autowired
	public UserRepository userRepository;

	public void Insert(User user) {
		userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	public User findByIdAux(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public Iterable<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	public User update(int id, User NewUser) {
		User user = userRepository.findById(id).orElse(null);
		updateData(user, NewUser);
		return userRepository.save(user);
	}

	public void updateData(User oldUser, User newUser) {
		oldUser.setName(newUser.getName());
		oldUser.setEmail(newUser.getEmail());
		oldUser.setPhone(newUser.getPhone());
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}
}
