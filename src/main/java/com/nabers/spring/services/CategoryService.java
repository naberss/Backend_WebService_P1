package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.Category;
import com.nabers.spring.repositories.CategoryRepository;

@Service
@Profile(value = "test")
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;

	public void InsertUpdate(Category category) {
		categoryRepository.save(category);
	}

	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}	

}
