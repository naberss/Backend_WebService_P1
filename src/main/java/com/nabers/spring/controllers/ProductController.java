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

import com.nabers.spring.entities.Product;
import com.nabers.spring.services.ProductService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public @ResponseBody ResponseEntity<Product> insert(@Valid @RequestBody Product product) {
		productService.Insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/findbyid/{id}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable(name = "id") int id) {
		Optional<Product> product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyname/{name}")
	public ResponseEntity<Iterable<Product>> findByName(@PathVariable(name = "name") String name) {
		Iterable<Product> products = productService.findByName(name);
		return ResponseEntity.ok().body(products);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findall")
	public ResponseEntity<Iterable<Product>> findAll() {
		Iterable<Product> allProducts = productService.findAll();
		return ResponseEntity.ok().body(allProducts);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
	public ResponseEntity<Product> update(@PathVariable(name = "id") Integer id, @RequestBody Product newProduct) {
		Product product = productService.update(id, newProduct);
		return ResponseEntity.accepted().body(product);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
	public ResponseEntity<Product> delete(@PathVariable(name = "id") Integer id) {
		productService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
