package com.henrique.ecommerce_back.controler;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.model.dto.ProductDTO;
import com.henrique.ecommerce_back.model.dto.ResponseDTO;
import com.henrique.ecommerce_back.service.management.ProductManagement.ProductManagementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductManagementController {

    private final ProductManagementService productManagementService;

    @PostMapping("/new-product")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody @Valid ProductDTO productDto,
            @RequestParam(name = "files", required = false) MultipartFile[] files) {
        ProductDTO product = productManagementService.newProduct(productDto);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Product created successfully", HttpStatus.OK.value(), product));
    }

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO> uploadProductImage(@RequestParam(required = true) UUID productId,
            @RequestParam(name = "files", required = true) MultipartFile[] files) {

        productManagementService.addProductImage(productId, files);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Image Uploaded successfully.", HttpStatus.OK.value(), null));
    }

    @DeleteMapping("/delete-image/{id}")
    public ResponseEntity<ResponseDTO> deleteProductImage(@PathVariable UUID id, @RequestParam String name) {
        productManagementService.removeProductImage(id, name);
        return ResponseEntity.ok().body(new ResponseDTO("Image deleted successfully!", HttpStatus.OK.value(), null));
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable UUID id) {
        productManagementService.deleteProduct(id);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Product deleted successfully", HttpStatus.OK.value(), null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> editProduct(@PathVariable UUID id, @RequestBody ProductDTO product) {
        productManagementService.editProduct(id, product);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Product edited successfully", HttpStatus.OK.value(), product));
    }

    @PostMapping("/increase-stock/{id}")
    public ResponseEntity<ResponseDTO> increaseStock(@PathVariable UUID id, @RequestParam Integer quantity) {
        productManagementService.increaseStock(id, quantity);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Stock increased successfully", HttpStatus.OK.value(), null));
    }

    @PostMapping("/decrease-stock/{id}")
    public ResponseEntity<ResponseDTO> decreaseStock(@PathVariable UUID id, @RequestParam Integer quantity) {
        productManagementService.decreaseStock(id, quantity);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Stock decreased successfully", HttpStatus.OK.value(), null));
    }

}
