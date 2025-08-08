package com.henrique.ecommerce_back.exceptions;

public class InvalidImageTypeException extends RuntimeException {

    public InvalidImageTypeException(String message) {
        super(message);
    }
}
