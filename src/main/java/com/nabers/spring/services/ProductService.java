package com.nabers.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.entities.Product;
import com.nabers.spring.repositories.ProductRepository;

@Service
@Profile(value = "dev")
public class ProductService {

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public OrderItemService orderItemService;

	public void Insert(Product Product) {
		productRepository.save(Product);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public Product findByIdAux(int id) {
		return productRepository.findById(id).orElse(null);
	}

	public Iterable<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	public Product update(Integer id, Product newProduct) {
		Product product = productRepository.findById(id).orElse(null);
		updateData(product, newProduct);
		productRepository.save(product);
		Iterable<OrderItem> orderitems = orderItemService.findByProduct(id);
		for (OrderItem ois : orderitems) {
			ois.setPrice(product.getPrice());
			orderItemService.Insert(ois);
		}
		return product;
	}

	public void updateData(Product oldProduct, Product newProduct) {
		oldProduct.setName(newProduct.getName());
		oldProduct.setDescription(newProduct.getDescription());
		oldProduct.setPrice(newProduct.getPrice());
	}

	public void deleteById(int id) {
		productRepository.deleteById(id);
	}

}
