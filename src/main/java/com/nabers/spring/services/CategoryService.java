package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.Category;
import com.nabers.spring.repositories.CategoryRepository;

@Service
@Profile(value = "dev")
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;

	public void Insert(Category category) {
		categoryRepository.save(category);
	}

	public Optional<Category> findById(int id) {
		return categoryRepository.findById(id);
	}

	public Iterable<Category> findByName(String name) {
		return categoryRepository.findByName(name);
	}

	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category update(Integer id, Category newCategory) {
		Category category = findById(id).orElse(null);
		updateData(category, newCategory);
		return categoryRepository.save(category);

	}

	public void updateData(Category oldCategory, Category newCategory) {
		oldCategory.setName(newCategory.getName());
	}

	public void deleteById(int id) {
		categoryRepository.deleteById(id);
	}

}
