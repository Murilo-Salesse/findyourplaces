package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends FindYourPlaceException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String title, String detail) {
		super(title, detail, HttpStatus.NOT_FOUND);
	}
}
