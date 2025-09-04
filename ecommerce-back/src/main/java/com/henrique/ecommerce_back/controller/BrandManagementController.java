package com.henrique.ecommerce_back.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.model.dto.BrandDTO;
import com.henrique.ecommerce_back.model.dto.ResponseDTO;
import com.henrique.ecommerce_back.service.management.BrandManagement.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/brands")
@RequiredArgsConstructor
public class BrandManagementController {

    private final BrandService brandService;

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAllBrands() {
        List<BrandDTO> brands = brandService.getAllBrands();

        return ResponseEntity.ok().body(new ResponseDTO("", HttpStatus.OK.value(), brands));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> newBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO brand = brandService.newBrand(brandDTO);

        return ResponseEntity.ok().body(new ResponseDTO("Brand created sucessfully", HttpStatus.OK.value(), brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().body(new ResponseDTO("Brand deleted sucessfully", HttpStatus.OK.value(), null));
    }

    @PostMapping(path = "/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO> uploadBrandImage(@PathVariable UUID id,
            @RequestParam(required = true) MultipartFile file) {

        brandService.addBrandImage(id, file);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Image Uploaded successfully.", HttpStatus.OK.value(), null));
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<ResponseDTO> deleteBrandImage(@PathVariable UUID id, @RequestParam String name) {
        brandService.removeBrandImage(id, name);
        return ResponseEntity.ok().body(new ResponseDTO("Image deleted successfully!", HttpStatus.OK.value(), null));
    }

}
