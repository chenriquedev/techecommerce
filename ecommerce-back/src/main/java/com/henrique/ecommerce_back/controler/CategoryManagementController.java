package com.henrique.ecommerce_back.controler;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.model.dto.CategoryDTO;
import com.henrique.ecommerce_back.model.dto.ResponseDTO;
import com.henrique.ecommerce_back.service.management.CategoryManagement.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryManagementController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.newCategory(categoryDTO);
        return ResponseEntity.ok(new ResponseDTO("", HttpStatus.CREATED.value(), category));

    }

     @PostMapping(path = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO> uploadProductImage(@RequestParam(required = true) UUID categoryId,
            @RequestParam( required = true) MultipartFile file) {

        categoryService.addCategoryImage(categoryId, file);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Image Uploaded successfully.", HttpStatus.OK.value(), null));
    }

    @DeleteMapping("/delete-image/{id}")
    public ResponseEntity<ResponseDTO> deleteProductImage(@PathVariable UUID id, @RequestParam String name) {
        categoryService.removeCategoryImage(id, name);
        return ResponseEntity.ok().body(new ResponseDTO("Image deleted successfully!", HttpStatus.OK.value(), null));
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok()
                .body(new ResponseDTO("Category deleted successfully", HttpStatus.OK.value(), null));
    }

    

}
