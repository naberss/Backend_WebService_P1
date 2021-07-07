package com.nabers.spring.controllers.Exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nabers.spring.services.Exceptions.ResourceNotFoundException;
import com.nabers.spring.services.Exceptions.DatabaseException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public static ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,
			                                                     HttpServletRequest request) {
		String error = "Resource Not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(value = DatabaseException.class)
	public static ResponseEntity<StandardError> DatabaseError(DatabaseException e, HttpServletRequest request) {
		String error = "Database Error";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);

	}

}
