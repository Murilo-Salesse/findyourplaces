package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public abstract class FindYourPlaceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String title;
	private final String detail;
	private final HttpStatus status;

	protected FindYourPlaceException(String title, String detail, HttpStatus status) {
		super(detail);
		this.title = title;
		this.detail = detail;
		this.status = status;
	}

	protected ProblemDetails toExceptionResponse() {
		return new ProblemDetails(
				new ExceptionResponse("about:blank", title, detail, status.value(), null),
				status.value());
	}
}
