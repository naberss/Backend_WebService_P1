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
import com.nabers.spring.entities.Product;
import com.nabers.spring.services.ProductService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo")
	public Product getProduct() {
		return new Product(1, "MACBOOK", "QUITE A NICE COMPUTER", 1500.0);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo2")
	ResponseEntity<Product> getProduct2() {
		Product product = new Product(1, "SAMSUNG BOOK", "QUITE A NICE COMPUTER", 1500.0);
		productService.InsertUpdate(product);
		product.getCategories().add(new Category(1, "Financial"));
		product.getCategories().add(new Category(2, "TESTE"));
		productService.InsertUpdate(product);
		return ResponseEntity.unprocessableEntity().body(product);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getinfo/{id}")
	public Optional<Product> findById(@PathVariable(name = "id") int id) {
		Optional<Product> product = productService.findById(id);
		return product;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/insertUpdate")
	public @ResponseBody ResponseEntity<Product> insertUpdate(@Valid Product product) {
		productService.InsertUpdate(product);
		return ResponseEntity.accepted().body(product);

	}
}
