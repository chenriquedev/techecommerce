package com.henrique.ecommerce_back.service;


import java.util.UUID;

import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;

public interface ProductService {
    PaginatedResponseDto<ProductDto> getAllProducts(PageDTO page);

    ProductDto getProductById(UUID Id);

    void getProductByName();

    void getProductsByCategory();

    void addProduct();

    void deleteProduct();

    void editProduct();

    void increaseStock();

    void decreaseStock();
}
