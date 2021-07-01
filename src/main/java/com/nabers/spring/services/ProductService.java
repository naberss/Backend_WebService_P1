package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.Product;
import com.nabers.spring.repositories.ProductRepository;

@Service
@Profile(value = "test")
public class ProductService {

	@Autowired
	public ProductRepository productRepository;

	public void InsertUpdate(Product Product) {
		productRepository.save(Product);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public void deleteById(int id) {
		productRepository.deleteById(id);
	}

}
