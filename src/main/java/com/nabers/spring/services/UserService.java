package com.nabers.spring.services;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.User;
import com.nabers.spring.repositories.UserRepository;
import com.nabers.spring.services.Exceptions.DatabaseException;
import com.nabers.spring.services.Exceptions.ResourceNotFoundException;

@Service
@Profile(value = "test")
public class UserService {

	@Autowired
	public UserRepository userRepository;

	public void Insert(User user) {
		userRepository.save(user);
	}

	public Optional<User> findById(Integer id) {
		return Optional.of(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	public User findByIdAux(int id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Iterable<User> findAll() {
		Iterable<User> users = userRepository.findAll();
		if (users != null) {
			return userRepository.findAll();
		}
		throw new ResourceNotFoundException(users);
	}

	public Iterable<User> findByName(String name) {
		Iterable<User> users = userRepository.findByName(name);
		if (users != null) {
			return userRepository.findByName(name);
		}
		throw new ResourceNotFoundException(users);
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
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			 throw new DatabaseException(e.getMessage()); 
		}

	}
}
