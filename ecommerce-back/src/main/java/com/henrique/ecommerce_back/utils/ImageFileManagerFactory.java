package com.henrique.ecommerce_back.utils;

import org.springframework.stereotype.Component;

@Component
public class ImageFileManagerFactory {
    public ImageFileManager create(String imagePath) {
        return new ImageFileManager(imagePath);
    }
}
