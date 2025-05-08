package com.henrique.ecommerce_back.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class ArgumentInvalidException extends RuntimeException {
    @Getter
    private HttpStatus status;
    public ArgumentInvalidException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
