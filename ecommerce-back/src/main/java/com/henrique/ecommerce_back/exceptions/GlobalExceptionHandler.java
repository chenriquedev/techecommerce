package com.henrique.ecommerce_back.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.henrique.ecommerce_back.model.dto.ResponseDTO;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFound(ProductNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), ex.getStatus().value(), null);
        return new ResponseEntity<>(responseDto, ex.getStatus());

    }

    @ExceptionHandler(ArgumentInvalidException.class)
    public ResponseEntity<ResponseDTO> handleArgumentInvalidException(ArgumentInvalidException ex) {

        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), ex.getStatus().value(), null);
        return new ResponseEntity<>(responseDto, ex.getStatus());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ResponseDTO response = new ResponseDTO("Validation error", HttpStatus.BAD_REQUEST.value(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
