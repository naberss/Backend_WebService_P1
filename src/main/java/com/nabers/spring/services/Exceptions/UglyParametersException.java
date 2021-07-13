package com.nabers.spring.services.Exceptions;

public class UglyParametersException extends RuntimeException{

	

	private static final long serialVersionUID = 3122323147568143228L;

	public UglyParametersException() {
		super( "Check the parameters, they are not looking good" );
	}

}
