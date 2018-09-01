package br.com.matheuscruz.projetomc.services.exceptions;

import org.springframework.http.HttpStatus;

public class ConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstraintViolationException(String message) {
		super(message);
	}

	public ConstraintViolationException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
	}

}
