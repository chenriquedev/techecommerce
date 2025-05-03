package com.henrique.ecommerce_back.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ProductDto>> getAllProducts(PageDTO page) {
        PaginatedResponseDto<ProductDto> products = productService.getAllProducts(page);
        return ResponseEntity.ok(products);
    }

}
