package com.henrique.ecommerce_back.service.management;

import com.henrique.ecommerce_back.model.dto.ProductDto;

public interface ProductManagementService {
    ProductDto newProduct(ProductDto productDto);

    void deleteProduct();

    void editProduct();

    void increaseStock();

    void decreaseStock();
}
