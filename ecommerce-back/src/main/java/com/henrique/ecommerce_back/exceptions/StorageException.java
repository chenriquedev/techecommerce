package com.henrique.ecommerce_back.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public class StorageException extends RuntimeException {
    @Getter
    private final HttpStatus status;

    public StorageException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    
}
