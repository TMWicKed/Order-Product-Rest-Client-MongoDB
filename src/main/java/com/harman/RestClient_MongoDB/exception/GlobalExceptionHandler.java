package com.harman.RestClient_MongoDB.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error>
    handleResourceNotFoundException(ResourceNotFoundException ex) {
        Error error = Error.builder()
                .errorCode("ResourceNotFound") // Define your custom error code here
                .errorMessage(ex.getMessage()) // Use the exception message as the error message
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}