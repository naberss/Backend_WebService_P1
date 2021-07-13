package com.nabers.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.Category;
import com.nabers.spring.repositories.CategoryRepository;
import com.nabers.spring.services.Exceptions.DatabaseException;
import com.nabers.spring.services.Exceptions.ResourceNotFoundException;

@Service
@Profile(value = { "dev", "test", "prod" })
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;

	public void Insert(Category category) {
		categoryRepository.save(category);
	}

	public Optional<Category> findById(int id) {
		return Optional.of(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));

	}

	public Iterable<Category> findByName(String name) {

		List<Category> categories = (List<Category>) categoryRepository.findByName(name);

		if (categories.size() != 0) {
			return categories;

		} else {
			throw new ResourceNotFoundException(name);
		}
	}

	public Iterable<Category> findAll() {

		List<Category> categories = (List<Category>) categoryRepository.findAll();

		if (categories.size() != 0) {
			return categories;

		} else {
			throw new ResourceNotFoundException();
		}

	}

	public Category update(Integer id, Category newCategory) {
		try {
			Category category = findById(id).orElse(null);
			updateData(category, newCategory);
			return categoryRepository.save(category);
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public void updateData(Category oldCategory, Category newCategory) {
		oldCategory.setName(newCategory.getName());
	}

	public void deleteById(int id) {
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		} catch (EmptyResultDataAccessException f) {
			throw new ResourceNotFoundException(id);
		}

	}

}
