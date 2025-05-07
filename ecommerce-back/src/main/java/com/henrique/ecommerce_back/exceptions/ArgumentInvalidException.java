package com.henrique.ecommerce_back.exceptions;

public class ArgumentInvalidException extends RuntimeException {
    public ArgumentInvalidException(String message) {
        super(message);
    }
}
