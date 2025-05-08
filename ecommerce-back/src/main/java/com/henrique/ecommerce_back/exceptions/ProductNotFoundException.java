package com.henrique.ecommerce_back.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ProductNotFoundException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public ProductNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
