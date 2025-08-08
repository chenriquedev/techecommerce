package com.henrique.ecommerce_back.exceptions;

public class BrandAlreadyExistsException extends RuntimeException {

    public BrandAlreadyExistsException(String message) {
        super(message);
    }
}
