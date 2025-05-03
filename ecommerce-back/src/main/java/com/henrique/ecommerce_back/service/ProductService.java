package com.henrique.ecommerce_back.service;


import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;

public interface ProductService {
    PaginatedResponseDto<ProductDto> getAllProducts(PageDTO page);

    void getProductById();

    void getProductByName();

    void getProductsByCategory();

    void addProduct();

    void deleteProduct();

    void editProduct();

    void increaseStock();

    void decreaseStock();
}
