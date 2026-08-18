package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends FindYourPlaceException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String title, String detail) {
		super(title, detail, HttpStatus.NOT_FOUND);
	}
}
