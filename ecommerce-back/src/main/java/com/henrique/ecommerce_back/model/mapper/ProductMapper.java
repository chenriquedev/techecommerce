package com.henrique.ecommerce_back.model.mapper;

import org.springframework.stereotype.Component;

import com.henrique.ecommerce_back.model.dto.CategoryDTO;
import com.henrique.ecommerce_back.model.dto.ProductDTO;
import com.henrique.ecommerce_back.model.entity.Product;

@Component
public class ProductMapper {
    public ProductDTO entityToDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        System.out.println(product);
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setPromotionalPrice(product.getPromotionalPrice());
        productDto.setIsActive(product.getIsActive());
        productDto.setImages(product.getImages());
        if (product.getStock() != null) {
            productDto.setStock(product.getStock().getQuantity());
        }
        if (product.getCategory() != null) {
            CategoryDTO categoryDto = new CategoryDTO();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategory(categoryDto);
        }
        // productDto.setSalesQuantity(product.getSalesQuantity());
        // productDto.setPixDiscount(product.getPixDiscount());
        // productDto.setOnSale(product.getOnSale());
        return productDto;
    }

    public Product dtoToEntity(ProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setPromotionalPrice(productDto.getPromotionalPrice());
        product.setIsActive(productDto.getIsActive());
        product.setImages(productDto.getImages());
        // product.setSalesQuantity(productDto.getSalesQuantity());
        // product.setPixDiscount(productDto.getPixDiscount());
        // product.setOnSale(productDto.getOnSale());
        return product;
    }
}
