package com.henrique.ecommerce_back.service.management.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.henrique.ecommerce_back.exceptions.ArgumentInvalidException;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.model.entity.Category;
import com.henrique.ecommerce_back.model.entity.Product;
import com.henrique.ecommerce_back.model.mapper.ProductMapper;
import com.henrique.ecommerce_back.repository.CategoryRepository;
import com.henrique.ecommerce_back.repository.ProductRepository;
import com.henrique.ecommerce_back.service.management.ProductManagementService;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto newProduct(ProductDto productDto) {
        Optional<Category> category = categoryRepository.findById(productDto.getCategory().getId());
        if (category.isEmpty()) {
            throw new ArgumentInvalidException("Category doesn't exists.", HttpStatus.BAD_REQUEST);
        }
        Product product = productMapper.dtoToEntity(productDto);
        product.setCategory(category.get());
        productRepository.save(product);
        return productMapper.entityToDto(product);
    }

    @Override
    public void deleteProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public void editProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editProduct'");
    }

    @Override
    public void increaseStock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'increaseStock'");
    }

    @Override
    public void decreaseStock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decreaseStock'");
    }

}
