package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class AdminException extends FindYourPlaceException {


	private static final long serialVersionUID = 1L;

	public AdminException(String title, String detail) {
        super(title, detail, HttpStatus.UNPROCESSABLE_CONTENT);
	}

}
