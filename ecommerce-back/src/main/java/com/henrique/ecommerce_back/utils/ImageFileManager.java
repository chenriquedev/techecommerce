package com.henrique.ecommerce_back.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.exceptions.StorageException;

public class ImageFileManager {

    private final String imagePath;

    public ImageFileManager(String imagePath) {
        this.imagePath = imagePath;
    }

    public String storeImage(MultipartFile file) {
        String fileName = generateImageName(file.getOriginalFilename());
        Path path = Paths.get(imagePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            file.transferTo(path.resolve(fileName));
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Error saving image '" + fileName + "'" + e);
        }
    }

    public void deleteImageFile(String imageName) {
        if (imageName.contains("..") || imageName.contains("/") || imageName.contains("\\")) {
            throw new StorageException("Invalid image name: " + imageName);
        }
        Path path = Paths.get(imagePath).resolve(imageName);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Error to delete image '" + imageName + "'" + e);
        }
    }

    private String generateImageName(String name) {
        if (name == null) {
            throw new StorageException("File name cannot be null");
        }
        Integer lastDotIndex = name.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == name.length() - 1) {
            throw new StorageException("File must have a valid extension");
        }
        String fileExtension = name.substring(lastDotIndex).toLowerCase();

        return UUID.randomUUID() + fileExtension;
    }
}
