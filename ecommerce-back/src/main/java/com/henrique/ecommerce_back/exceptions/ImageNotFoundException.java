package com.henrique.ecommerce_back.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ImageNotFoundException extends RuntimeException {
     @Getter
    private final HttpStatus status;

    public ImageNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
