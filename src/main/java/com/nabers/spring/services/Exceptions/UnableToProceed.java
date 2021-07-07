package com.nabers.spring.services.Exceptions;

public class UnableToProceed extends RuntimeException {

	private static final long serialVersionUID = -4246976099761588494L;

	public UnableToProceed(Object id) {
		super("Unable To Handle . ID - " + id);
	}

}
