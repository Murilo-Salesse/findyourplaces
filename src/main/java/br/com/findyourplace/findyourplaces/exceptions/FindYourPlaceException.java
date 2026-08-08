package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;

public class FindYourPlaceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected ProblemDetails toExceptionResponse() {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ProblemDetails(
                new ExceptionResponse(
                        "about:blank",
                        "FindYourPlace Exception",
                        "There is an internal server error",
                        status.value(),
                        null
                ),
                status.value()
        );
    }
}