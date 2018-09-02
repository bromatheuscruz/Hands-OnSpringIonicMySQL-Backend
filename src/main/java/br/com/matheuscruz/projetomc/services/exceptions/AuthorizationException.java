package br.com.matheuscruz.projetomc.services.exceptions;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
	}

}
