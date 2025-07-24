package com.henrique.ecommerce_back.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ProductNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Not Found");
        response.put("message", ex.getMessage());
        response.put("status", String.valueOf(ex.getStatus().value()));

        return new ResponseEntity<>(response, ex.getStatus());

    }
}
