package com.henrique.ecommerce_back.exceptions;

public class CategoryImageNotFoundException extends  RuntimeException{
    public CategoryImageNotFoundException(String message) {
        super(message);
    }
}
