package com.airline.Exceptions;

import com.airline.DTOs.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // Tells Spring to use this class for global error handling
public class GlobalExceptionHandler {

    // This method catches any standard RuntimeException thrown in your services
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {
        
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage() // This will grab your "Not enough available seats" message
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // You can add more handlers here later, like for ValidationExceptions 
    // or Database connection errors, each returning a specific HTTP status!
}