package br.com.matheuscruz.projetomc.services.exceptions;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
	}

}
