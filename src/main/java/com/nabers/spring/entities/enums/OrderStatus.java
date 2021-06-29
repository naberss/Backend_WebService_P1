package com.nabers.spring.entities.enums;

public enum OrderStatus {
	WAITING_PAYMENT(0), PAID(1), SHIPPED(2), DELIVERED(3), CANCELED(4);

	private int id;

	OrderStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static OrderStatus valueOf(int id) {

		for (OrderStatus order : OrderStatus.values()) {
			if (order.getId() == id) {
				return order;
			}
		}
		throw new IllegalArgumentException("Parameter Not Found");

	}

}
