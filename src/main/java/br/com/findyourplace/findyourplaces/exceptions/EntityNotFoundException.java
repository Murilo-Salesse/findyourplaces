package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends FindYourPlaceException {

	private static final long serialVersionUID = 1L;

	private final String detail;
	private final String title;

	public EntityNotFoundException(String title, String detail) {
		this.title = title;
		this.detail = detail;
	}

	@Override
	protected ProblemDetails toExceptionResponse() {

		@SuppressWarnings("deprecation")
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

		return new ProblemDetails(new ExceptionResponse("about:blank", title, detail, status.value(), null),
				status.value());
	}
}