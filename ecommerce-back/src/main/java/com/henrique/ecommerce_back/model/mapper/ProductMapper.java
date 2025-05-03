package com.henrique.ecommerce_back.model.mapper;

import org.springframework.stereotype.Component;

import com.henrique.ecommerce_back.model.dto.CategoryDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.model.entity.Product;

@Component
public class ProductMapper {
    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setName(product.getCategory().getName());
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setSalesQuantity(product.getSalesQuantity());
        productDto.setPixDiscount(product.getPixDiscount());
        productDto.setOnSale(product.getOnSale());
        productDto.setPromotionalPrice(product.getPromotionalPrice());
        productDto.setCategory(categoryDto);
        return productDto;
    }
}
