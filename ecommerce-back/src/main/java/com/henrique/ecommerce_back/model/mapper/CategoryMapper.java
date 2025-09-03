package com.henrique.ecommerce_back.model.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.henrique.ecommerce_back.model.dto.CategoryDTO;
import com.henrique.ecommerce_back.model.entity.Category;

@Component
public class CategoryMapper {

    @Value("${default-image}")
    private String defaultImage;

    public Category dtoToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setImage(dto.getImage() != null && dto.getImage().contains("default") ? null : dto.getImage());
        category.setName(dto.getName());
        return category;
    }

    public CategoryDTO entityToDto(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setImage(
                categoryDTO.getImage() == null || categoryDTO.getImage().isBlank() ? "/category-images/" + defaultImage
                        : "/category-images/" + categoryDTO.getImage());
        categoryDTO.setName(categoryDTO.getName());
        return categoryDTO;
    }
}
