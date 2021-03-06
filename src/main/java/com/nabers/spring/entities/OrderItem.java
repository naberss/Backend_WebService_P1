package com.nabers.spring.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nabers.spring.entities.pk.OrderItemPK;

@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();
	private Integer quantity;
	@SuppressWarnings("unused")
	private Double Price;

	public OrderItem() {
	}

	public OrderItem(Product product, Order order, Integer quantity) {
		super();
		id.setProduct(product);
		id.setOrder(order);
		this.quantity = quantity;
		this.Price = id.getProduct().getPrice();
	}

	public OrderItemPK getId() {
		return id;
	}

	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	@JsonIgnore
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return this.getProduct().getPrice();
	}

	public void setPrice(Double price) {
		this.Price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}

	public Double getSubtotal() {
		return (this.getProduct().getPrice() * this.quantity);

		
	}

}
