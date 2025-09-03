package com.henrique.ecommerce_back.service.management.ProductManagement;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.model.dto.ProductDTO;

public interface ProductManagementService {
    ProductDTO newProduct(ProductDTO productDto);

    void addProductImage(UUID productId, MultipartFile[] files);

    void removeProductImage(UUID productId, String imageName);

    void deleteProduct(UUID productId);

    void editProduct(UUID productId, ProductDTO productDto);

    void increaseStock(UUID productId, Integer quantity);

    void decreaseStock(UUID productId, Integer quantity);
}
