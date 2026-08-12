package br.com.findyourplace.findyourplaces.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.findyourplace.findyourplaces.controller.dto.response.InvalidParamsResponseDTO;

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
    
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException exception) {

        var status = HttpStatus.UNAUTHORIZED;

        var response = new ExceptionResponse(
                "about:blank",
                "Falha na autenticação",
                "E-mail ou senha inválidos.",
                status.value(),
                null
        );

        return ResponseEntity
                .status(status)
                .body(response);
    }
}