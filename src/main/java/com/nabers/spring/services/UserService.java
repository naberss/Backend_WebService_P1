package com.nabers.spring.services;

import java.util.List;
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

	public List<User> findAll() {
		return userRepository.findAll();
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

	@SuppressWarnings("deprecation")
	public User update(int id, User obj) {
		User user = userRepository.getOne(id);
		updateData(user, obj);
		return userRepository.save(user);

	}

	public void updateData(User user, User obj) {
		user.setPhone(obj.getPhone());
		user.setEmail(obj.getEmail());
		user.setName(obj.getName());
	}

}
