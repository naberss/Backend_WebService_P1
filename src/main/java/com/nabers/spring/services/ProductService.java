package com.nabers.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.entities.Product;
import com.nabers.spring.repositories.ProductRepository;
import com.nabers.spring.services.Exceptions.DatabaseException;
import com.nabers.spring.services.Exceptions.ResourceNotFoundException;

@Service
@Profile(value = { "dev", "test", "prod" })
public class ProductService {

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public OrderItemService orderItemService;

	public void Insert(Product Product) {
		productRepository.save(Product);
	}

	public Optional<Product> findById(int id) {
		return Optional.of(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	public Product findByIdAux(int id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Iterable<Product> findByName(String name) {
		List<Product> products = (List<Product>) productRepository.findByName(name);

		if (products.size() != 0) {
			return products;
		}
		throw new ResourceNotFoundException(name);
	}

	public Iterable<Product> findAll() {
		List<Product> products = (List<Product>) productRepository.findAll();

		if (products.size() != 0) {
			return products;
		}
		throw new ResourceNotFoundException();
	}

	public Product update(Integer id, Product newProduct) {
		try {

			Product product = productRepository.findById(id).orElse(null);
			updateData(product, newProduct);
			productRepository.save(product);
			Iterable<OrderItem> orderitems = orderItemService.findByProduct(id);
			for (OrderItem ois : orderitems) {
				ois.setPrice(product.getPrice());
				orderItemService.Insert(ois);
			}
			return product;
		} catch (NullPointerException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public void updateData(Product oldProduct, Product newProduct) {
		oldProduct.setName(newProduct.getName());
		oldProduct.setDescription(newProduct.getDescription());
		oldProduct.setPrice(newProduct.getPrice());
	}

	public void deleteById(int id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException f) {
			throw new DatabaseException(f.getMessage());
		}
	}

}
