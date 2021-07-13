package com.nabers.spring.services.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = -2826283683208559290L;
	
	
	public ResourceNotFoundException(Object id) {
		super("Resource Not Found. ID "+ id);
	}
	
	public ResourceNotFoundException() {
		super("Resource Not Found. ID ");
	}

}
