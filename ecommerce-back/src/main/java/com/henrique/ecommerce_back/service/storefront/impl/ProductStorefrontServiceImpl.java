package com.henrique.ecommerce_back.service.storefront.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.henrique.ecommerce_back.exceptions.ArgumentInvalidException;
import com.henrique.ecommerce_back.exceptions.ProductNotFoundException;
import com.henrique.ecommerce_back.model.dto.FilterProductDto;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.model.mapper.ProductMapper;
import com.henrique.ecommerce_back.repository.ProductRepository;
import com.henrique.ecommerce_back.service.storefront.ProductStorefrontService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductStorefrontServiceImpl implements ProductStorefrontService {

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
    public ProductDto getProductById(String id) {
        try {
            UUID productId = UUID.fromString(id);
            return productRepository.findById(productId).map(productMapper::entityToDto)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            throw new ArgumentInvalidException("Product doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public PaginatedResponseDto<ProductDto> filterProducts(FilterProductDto filterProductDto, PageDTO page) {
        page.sanitizePageParameters();
        String namePattern = filterProductDto.getName() == null ? null : "%" + filterProductDto.getName() + "%";
        Page<ProductDto> products = productRepository.filterProducts(
                namePattern,
                filterProductDto.getCategory(),
                filterProductDto.getOnSale(),
                filterProductDto.getPriceMin(),
                filterProductDto.getPriceMax(),
                filterProductDto.getBrand(),
                PageRequest.of(page.getPage(), page.getSize())).map(productMapper::entityToDto);

        PaginatedResponseDto<ProductDto> response = new PaginatedResponseDto<>(
                products.getContent(),
                products.getSize(),
                products.getNumber(),
                products.getTotalElements());

        return response;
    }
}
