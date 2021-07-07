package com.nabers.spring.services.Exceptions;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = -4246976099761588494L;

	public DatabaseException(String msg) {
		super(msg);
	}

}
