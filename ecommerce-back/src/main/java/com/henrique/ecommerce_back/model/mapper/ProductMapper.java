package com.henrique.ecommerce_back.model.mapper;

import org.springframework.stereotype.Component;

import com.henrique.ecommerce_back.model.dto.CategoryDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.model.entity.Product;

@Component
public class ProductMapper {
    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setSalesQuantity(product.getSalesQuantity());
        productDto.setPixDiscount(product.getPixDiscount());
        productDto.setOnSale(product.getOnSale());
        productDto.setPromotionalPrice(product.getPromotionalPrice());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setSalesQuantity(productDto.getSalesQuantity());
        product.setPixDiscount(productDto.getPixDiscount());
        product.setOnSale(productDto.getOnSale());
        product.setPromotionalPrice(productDto.getPromotionalPrice());
        return product;
    }
}
