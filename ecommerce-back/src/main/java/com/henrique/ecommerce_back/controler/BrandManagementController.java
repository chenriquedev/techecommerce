package com.henrique.ecommerce_back.controler;

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
@RequestMapping("/admin/brand")
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok().body(new ResponseDTO("Brand deleted sucessfully", HttpStatus.OK.value(), null));
    }

    @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO> uploadBrandImage(@RequestParam(required = true) UUID brandID,
            @RequestParam(required = true) MultipartFile file) {

        brandService.addBrandImage(brandID, file);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Image Uploaded successfully.", HttpStatus.OK.value(), null));
    }

    @DeleteMapping("/delete-image/{brandID}")
    public ResponseEntity<ResponseDTO> deleteBrandImage(@PathVariable UUID brandID, @RequestParam String name) {
        brandService.removeBrandImage(brandID, name);
        return ResponseEntity.ok().body(new ResponseDTO("Image deleted successfully!", HttpStatus.OK.value(), null));
    }

}
