package com.nabers.spring.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nabers.spring.entities.Category;
import com.nabers.spring.services.CategoryService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public @ResponseBody ResponseEntity<Category> insert(@Valid @RequestBody Category category) {
		categoryService.Insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).body(category);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyid/{id}")
	public Optional<Category> findById(@PathVariable(name = "id") int id) {
		Optional<Category> category = categoryService.findById(id);
		return category;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findall")
	public ResponseEntity<Iterable<Category>> findAll() {
		Iterable<Category> allCategories = categoryService.findAll();
		return ResponseEntity.ok().body(allCategories);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyname/{name}")
	public ResponseEntity<Iterable<Category>> findByName(@PathVariable(name = "name") String name) {
		Iterable<Category> categories = categoryService.findByName(name);
		return ResponseEntity.ok().body(categories);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public @ResponseBody ResponseEntity<Category> update(@PathVariable(name = "id") Integer id, @RequestBody Category Newcategory) {
		Category category = categoryService.update(id, Newcategory);
		return ResponseEntity.accepted().body(category);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE,path = "/delete/{id}")
	public ResponseEntity<Category> delete(@PathVariable(name = "id") Integer id){
		categoryService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
