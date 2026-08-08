package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.findyourplace.findyourplaces.controller.dto.InvalidParamsResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FindYourPlaceException.class)
    public ResponseEntity<ExceptionResponse> handleFindYourPlaceException(
            FindYourPlaceException exception
    ) {

        var problemDetails = exception.toExceptionResponse();

        return ResponseEntity
                .status(problemDetails.status())
                .body(problemDetails.response());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exception) {

        var invalidParams = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new InvalidParamsResponseDTO(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        var response = new ExceptionResponse(
                "about:blank",
                "Invalid parameters",
                "One or more fields are invalid",
                HttpStatus.BAD_REQUEST.value(),
                invalidParams
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}