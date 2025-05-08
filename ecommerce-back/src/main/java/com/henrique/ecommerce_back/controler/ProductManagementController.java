package com.henrique.ecommerce_back.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.ecommerce_back.model.dto.ProductDto;
import com.henrique.ecommerce_back.model.dto.ResponseDTO;
import com.henrique.ecommerce_back.service.management.ProductManagementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/admin/products")
@RequiredArgsConstructor
public class ProductManagementController {

    @Autowired
    private ProductManagementService productManagementService;

    @PostMapping("/new-product")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody @Valid ProductDto productDto) {
        ProductDto product = productManagementService.newProduct(productDto);
        return ResponseEntity.ok()
                .body(new ResponseDTO
                (" Product created successfully", HttpStatus.OK.value(), product));
    }

}
