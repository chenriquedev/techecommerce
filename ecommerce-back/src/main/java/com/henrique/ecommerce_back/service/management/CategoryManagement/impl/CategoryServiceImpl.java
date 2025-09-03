package com.henrique.ecommerce_back.service.management.CategoryManagement.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.exceptions.CategoryAlreadyExistsException;
import com.henrique.ecommerce_back.exceptions.CategoryImageNotFoundException;
import com.henrique.ecommerce_back.exceptions.CategoryNotFoundException;
import com.henrique.ecommerce_back.exceptions.StorageException;
import com.henrique.ecommerce_back.model.dto.CategoryDTO;
import com.henrique.ecommerce_back.model.entity.Category;
import com.henrique.ecommerce_back.model.mapper.CategoryMapper;
import com.henrique.ecommerce_back.repository.CategoryRepository;
import com.henrique.ecommerce_back.service.management.CategoryManagement.CategoryService;
import com.henrique.ecommerce_back.utils.AnalyseImageType;
import com.henrique.ecommerce_back.utils.ImageFileManager;
import com.henrique.ecommerce_back.utils.ImageFileManagerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Value("${category.image.path}")
    private String categoryImagePath;

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final AnalyseImageType analyseImageType;
    private final ImageFileManagerFactory imageFileManagerFactory;

    @Override
    public CategoryDTO newCategory(CategoryDTO categoryDTO) {
        Optional<Category> categoryExists = categoryRepository.findByNameIgnoreCase(categoryDTO.getName());
        if (categoryExists.isPresent()) {
            throw new CategoryAlreadyExistsException(
                    String.format("Category %s already exists", categoryExists.get().getName()));
        }

        Category newCategory = categoryRepository.save(categoryMapper.dtoToEntity(categoryDTO));

        return categoryMapper.entityToDto(newCategory);
    }

    @Override
    public void deleteCategory(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category doesn't exists.");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public void addCategoryImage(UUID categoryId, MultipartFile file) {
        ImageFileManager manager = imageFileManagerFactory.create(categoryImagePath);
        Category category = getCategoryOrThrow(categoryId);
        String oldImagePath = null;

        analyseImageType.validateImageType(file.getContentType());

        try {
            if (category.getImage() != null && !category.getImage().isBlank()) {
                oldImagePath = category.getImage();
            }
            String fileName = manager.storeImage(file);
            category.setImage(fileName);
            categoryRepository.saveAndFlush(category);
            if (oldImagePath != null) {
                manager.deleteImageFile(oldImagePath);
            }
        } catch (Exception e) {
            throw new StorageException("Error to save image");
        }
    }

    @Override
    public void removeCategoryImage(UUID categoryId, String imageName) {
        ImageFileManager manager = imageFileManagerFactory.create(categoryImagePath);
        Category category = getCategoryOrThrow(categoryId);
        if (category.getImage() == null || !category.getImage().equals(imageName)) {
            throw new CategoryImageNotFoundException("Category image doesn't exist");
        }
        category.setImage(null);
        categoryRepository.saveAndFlush(category);
        manager.deleteImageFile(imageName);
    }

    private Category getCategoryOrThrow(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }
}
