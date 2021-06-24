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

	public void InsertUpdate(User user) {
		userRepository.save(user);
	}

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public void deleteByName(String name) {
		userRepository.deleteByName(name);
	}

	public void containName(String name) {
		userRepository.ContainName(name);
	}

}
