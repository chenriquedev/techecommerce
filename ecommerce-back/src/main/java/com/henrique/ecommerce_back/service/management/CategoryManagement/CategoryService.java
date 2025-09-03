package com.henrique.ecommerce_back.service.management.CategoryManagement;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.model.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO newCategory(CategoryDTO categoryDTO);

    void deleteCategory(UUID id);

    void addCategoryImage(UUID categoryId, MultipartFile file);

    void removeCategoryImage(UUID categoryId, String imageName);
}
