package com.nabers.spring.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nabers.spring.entities.OrderItem;
import com.nabers.spring.services.OrderItemService;
import com.nabers.spring.services.OrderService;
import com.nabers.spring.services.ProductService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/orderitems")
public class OrdemItemController {

	@Autowired
	OrderItemService ordemItemService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, path = "/insert")
	public @ResponseBody ResponseEntity<OrderItem> insert(@Param("product_id") Integer product_id,
			                                                    @Param("order_id") Integer order_id, 
			                                                    @Param("quantity") Integer quantity) {
		OrderItem orderitem = new OrderItem(productService.findByIdAux(product_id), 
				                            orderService.findByIdAux(order_id),
				                            quantity);
		ordemItemService.Insert(orderitem);
		ServletUriComponentsBuilder.fromCurrentRequest();
		URI uri = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(orderitem.getId()).toUri();
		return ResponseEntity.created(uri).body(orderitem);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/findbyid/{product_id}/{order_id}")
	public ResponseEntity<Optional<OrderItem>> findById(@PathVariable(name = "product_id") Integer product_id,
			                                            @PathVariable(name = "order_id") Integer order_id) {
		Optional<OrderItem> orderitems = ordemItemService.findById(product_id, order_id);
		return ResponseEntity.ok().body(orderitems);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/findbyproduct/{product_id}")
	public ResponseEntity<Iterable<OrderItem>> findByProduct(@PathVariable(name = "product_id") Integer product_id) {
		Iterable<OrderItem> orderitem = ordemItemService.findByProduct(product_id);
		return ResponseEntity.ok().body(orderitem);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/findbyorder/{order_id}")
	public ResponseEntity<Iterable<OrderItem>> findByOrder(@PathVariable(name = "order_id") Integer order_id) {
		Iterable<OrderItem> orderitems = ordemItemService.findByOrder(order_id);
		return ResponseEntity.ok().body(orderitems);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/findall")
	public ResponseEntity<Iterable<OrderItem>> findAll() {
		Iterable<OrderItem> allOrderitems = ordemItemService.findAll();
		return ResponseEntity.ok().body(allOrderitems);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/update/{product_id}/{order_id}")
	public ResponseEntity<OrderItem> update(@PathVariable(name = "product_id") Integer product_id,
                                            @PathVariable(name = "order_id") Integer order_id,
                                            @Param(value = "newProduct_Id") Integer newProduct_Id,
                                            @Param(value = "quantity") Integer quantity) {
		OrderItem newOrderItem = new OrderItem();
		if(newProduct_Id != null) {
			newOrderItem.setProduct(productService.findByIdAux(newProduct_Id));
		}
		newOrderItem.setQuantity(quantity);
		newOrderItem = ordemItemService.update(product_id, order_id, newOrderItem);
		return ResponseEntity.accepted().body(newOrderItem);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,path = "/deletebyid/{product_id}/{order_id}")
	public ResponseEntity<OrderItem> deleteById(@PathVariable(name = "product_id") Integer product_id, @PathVariable(name = "order_id") Integer order_id){
		ordemItemService.deleteById(product_id, order_id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
