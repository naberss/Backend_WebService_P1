package com.nabers.spring.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nabers.spring.entities.Category;
import com.nabers.spring.services.CategoryService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public Category getCategory() {
		return new Category(1, "FINANCIAL");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo2")
	ResponseEntity<Category> getCategory2() {
		Category category = new Category(135, "TESTE");
		categoryService.InsertUpdate(category);
		return ResponseEntity.unprocessableEntity().body(category);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo/{id}")
	public Optional<Category> findById(@PathVariable(name = "id") int id) {
		Optional<Category> category = categoryService.findById(id);
		return category;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertUpdate")
	public @ResponseBody ResponseEntity<Category> insertUpdate(@Valid Category category) {
		categoryService.InsertUpdate(category);
		return ResponseEntity.accepted().body(category);

	}
}
