package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateUserException extends FindYourPlaceException {

    private static final long serialVersionUID = 1L;

    public DuplicateUserException(String title, String detail) {
        super(title, detail, HttpStatus.CONFLICT);
    }
}
