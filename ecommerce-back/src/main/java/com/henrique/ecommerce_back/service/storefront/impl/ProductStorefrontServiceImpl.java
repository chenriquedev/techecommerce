package com.henrique.ecommerce_back.service.storefront.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.henrique.ecommerce_back.exceptions.ArgumentInvalidException;
import com.henrique.ecommerce_back.exceptions.ProductNotFoundException;
import com.henrique.ecommerce_back.model.dto.FilterProductDTO;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDTO;
import com.henrique.ecommerce_back.model.dto.ProductDTO;
import com.henrique.ecommerce_back.model.entity.Product;
import com.henrique.ecommerce_back.model.mapper.ProductMapper;
import com.henrique.ecommerce_back.repository.ProductRepository;
import com.henrique.ecommerce_back.repository.specifications.ProductSpecifications;
import com.henrique.ecommerce_back.service.storefront.ProductStorefrontService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductStorefrontServiceImpl implements ProductStorefrontService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public PaginatedResponseDTO<ProductDTO> getAllProducts(PageDTO page, Boolean isActive) {
        page.sanitizePageParameters();
        Specification<Product> specs = ProductSpecifications.activeProducts(isActive);
        Page<ProductDTO> products = productRepository
                .findAll(specs, PageRequest.of(page.getPage(), page.getSize()))
                .map(productMapper::entityToDto);
                System.out.println(products.getContent());
        return PaginatedResponseDTO.fromPage(products);
    }

    @Override
    public ProductDTO getProductById(String id) {
        try {
            UUID productId = UUID.fromString(id);
            return productRepository.findById(productId).map(productMapper::entityToDto)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found", HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            throw new ArgumentInvalidException("Product doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public PaginatedResponseDTO<ProductDTO> filterProducts(FilterProductDTO filterProductDto, PageDTO page) {
        page.sanitizePageParameters();
        String namePattern = filterProductDto.getName() == null ? null : "%" + filterProductDto.getName() + "%";
        Page<ProductDTO> products = productRepository.filterProducts(
                namePattern,
                filterProductDto.getCategory(),
                // filterProductDto.getOnSale(),
                filterProductDto.getPriceMin(),
                filterProductDto.getPriceMax(),
                filterProductDto.getBrand(),
                PageRequest.of(page.getPage(), page.getSize())).map(productMapper::entityToDto);

        return PaginatedResponseDTO.fromPage(products);
    }
}
