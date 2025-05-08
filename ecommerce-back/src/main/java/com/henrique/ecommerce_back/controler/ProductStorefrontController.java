package com.henrique.ecommerce_back.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.ecommerce_back.model.dto.FilterProductDto;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.service.storefront.ProductStorefrontService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductStorefrontController {

    private final ProductStorefrontService productService;

    @GetMapping
    public ResponseEntity<PaginatedResponseDto<ProductDto>> getAllProducts(PageDTO page) {
        PaginatedResponseDto<ProductDto> products = productService.getAllProducts(page);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/filter")
    public ResponseEntity<PaginatedResponseDto<ProductDto>> getProductsByFilter(@ModelAttribute PageDTO pageDTO,
            @ModelAttribute FilterProductDto filterProductDto) {
        PaginatedResponseDto<ProductDto> product = productService.filterProducts(filterProductDto, pageDTO);
        return ResponseEntity.ok(product);
    }

}
