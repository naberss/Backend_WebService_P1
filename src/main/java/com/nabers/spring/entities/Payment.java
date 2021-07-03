package com.nabers.spring.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	@OneToOne
	@MapsId
	private Order order;

	public Payment() {
	}

	public Payment(Integer id, Instant moment, Order order) {
		super();
		this.id = id;
		this.moment = moment;
		this.order = order;
	}

	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	
	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

}
