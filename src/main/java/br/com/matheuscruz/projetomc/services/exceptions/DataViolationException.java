package br.com.matheuscruz.projetomc.services.exceptions;

import org.springframework.http.HttpStatus;

public class DataViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataViolationException(String message) {
		super(message);
	}

	public DataViolationException(String message, Throwable cause, HttpStatus status) {
		super(message, cause);
	}

}
