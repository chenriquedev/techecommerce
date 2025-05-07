package com.henrique.ecommerce_back.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.henrique.ecommerce_back.exceptions.ArgumentInvalidException;
import com.henrique.ecommerce_back.exceptions.ProductNotFoundException;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.model.mapper.ProductMapper;
import com.henrique.ecommerce_back.repository.ProductRepository;
import com.henrique.ecommerce_back.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public PaginatedResponseDto<ProductDto> getAllProducts(PageDTO page) {
        page.sanitizePageParameters();
        Page<ProductDto> products = productRepository.findAll(PageRequest.of(page.getPage(), page.getSize()))
                .map(productMapper::entityToDto);
        PaginatedResponseDto<ProductDto> response = new PaginatedResponseDto<>(
                products.getContent(),
                products.getSize(),
                products.getNumber(),
                products.getTotalElements());

        return response;
    }

    @Override
    public ProductDto getProductById(UUID id) {
        if (id == null) {
            throw new ArgumentInvalidException("Product doesn't exist");
        }

        return productRepository.getProductById(id).map(productMapper::entityToDto)
                .orElseThrow(() -> new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void getProductByName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void getProductsByCategory() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addProduct() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteProduct() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void editProduct() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void increaseStock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void decreaseStock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
