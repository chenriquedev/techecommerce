package com.henrique.ecommerce_back.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.henrique.ecommerce_back.exceptions.InvalidImageTypeException;

@Component
public class AnalyseImageType {
    public void validateImageType(String contentType) {
        List<String> allowedTypes = List.of("image/png", "image/jpeg", "image/jpg");
        if (contentType == null ||
                !allowedTypes.contains(contentType)) {
            throw new InvalidImageTypeException("Invalid image type.");
        }
    }
}
