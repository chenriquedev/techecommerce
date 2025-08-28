package com.henrique.ecommerce_back.controler;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.ecommerce_back.model.dto.FilterProductDTO;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDTO;
import com.henrique.ecommerce_back.model.dto.ProductDTO;
import com.henrique.ecommerce_back.service.storefront.ProductStorefrontService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductStorefrontController {

    private final ProductStorefrontService productService;

    @GetMapping
    public ResponseEntity<PaginatedResponseDTO<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "true") Boolean active, PageDTO page) {
        PaginatedResponseDTO<ProductDTO> products = productService.getAllProducts(page, active);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/filter")
    public ResponseEntity<PaginatedResponseDTO<ProductDTO>> getProductsByFilter(@ModelAttribute PageDTO pageDTO,
            @ModelAttribute FilterProductDTO filterProductDto) {
        PaginatedResponseDTO<ProductDTO> product = productService.filterProducts(filterProductDto, pageDTO);
        return ResponseEntity.ok(product);
    }

}
