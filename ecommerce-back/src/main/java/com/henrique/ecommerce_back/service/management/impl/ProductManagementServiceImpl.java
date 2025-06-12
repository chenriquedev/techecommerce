package com.henrique.ecommerce_back.service.management.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.exceptions.ArgumentInvalidException;
import com.henrique.ecommerce_back.exceptions.ImageNotFoundException;
import com.henrique.ecommerce_back.exceptions.StorageException;
import com.henrique.ecommerce_back.model.dto.ProductDTO;
import com.henrique.ecommerce_back.model.entity.Category;
import com.henrique.ecommerce_back.model.entity.Product;
import com.henrique.ecommerce_back.model.entity.Stock;
import com.henrique.ecommerce_back.model.enums.StockChangeReason;
import com.henrique.ecommerce_back.model.mapper.ProductMapper;
import com.henrique.ecommerce_back.repository.CategoryRepository;
import com.henrique.ecommerce_back.repository.ProductRepository;
import com.henrique.ecommerce_back.repository.StockRepository;
import com.henrique.ecommerce_back.service.management.ProductManagementService;
import com.henrique.ecommerce_back.service.management.StockManagement.StockMovementService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {
    @Value("${image.path}")
    private String imagePath;

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockMovementService stockMovementService;
    private final StockRepository stockRepository;

    @Override
    @Transactional
    public ProductDTO newProduct(ProductDTO productDto)
            throws IllegalStateException {
        Optional<Category> category = categoryRepository.findById(productDto.getCategory().getId());
        if (category.isEmpty()) {
            throw new ArgumentInvalidException("Category doesn't exists.", HttpStatus.BAD_REQUEST);
        }
        Product product = productMapper.dtoToEntity(productDto);
        product.setIsActive(true);

        Stock stock = new Stock();
        stock.setQuantity(productDto.getStock());
        product.setStock(stock);

        product.setCategory(category.get());

        productRepository.save(product);
        return productMapper.entityToDto(product);
    }

    @Override
    public void addProductImage(UUID productId, MultipartFile[] files) {
        Product product = getProductOrThrow(productId);
        if (product.getImages() == null) {
            product.setImages(new ArrayList<>());
        }
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                String filePath = fileName;

                product.getImages().add(filePath);
                try {
                    Path path = Paths.get("images");
                    if (!Files.exists(path)) {
                        Files.createDirectories(path);
                    }
                    file.transferTo(path.resolve(fileName));
                } catch (IOException e) {
                    System.err.println("Error saving file: " + e.getMessage());
                }

            }
        }
        productRepository.save(product);
    }

    @Override
    public void removeProductImage(UUID productId, String imageName) {
        Product product = getProductOrThrow(productId);
        Boolean imageExists = product.getImages().contains(imageName);
        if (!imageExists) {
            throw new ImageNotFoundException("Image not found.", HttpStatus.NOT_FOUND);
        }
        Path path = Paths.get(imagePath + "/" + imageName);

        try {
            Files.deleteIfExists(path);
            product.getImages().remove(imageName);
            productRepository.save(product);
        } catch (IOException e) {
            throw new StorageException("Error to delete image '" + imageName + "'" + e,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        Product product = getProductOrThrow(productId);
        product.setIsActive(false);
        productRepository.save(product);
    }

    @Override
    public void editProduct(UUID productId, ProductDTO productDto) {
        // TODO
        /**
         * ? This method should update images and category as well,
         * ? when change brand to entity, the method should also update brand.
         * ? When changing the category, it should check if the category exists.
         * ? If the category does not exist, it should throw an exception.
         */
        Product product = getProductOrThrow(productId);
        Product updatedProduct = changeNonNullFields(product, productDto);
        productRepository.save(updatedProduct);
    }

    @Override
    public void increaseStock(UUID productId, Integer quantity) {
        if (quantity <= 0) {
            throw new ArgumentInvalidException("Quantity must be greater than zero", HttpStatus.BAD_REQUEST);
        }
        Product product = getProductOrThrow(productId);
        Stock stock = product.getStock();
        stock.setQuantity(stock.getQuantity() + quantity);
        stockMovementService.registerMovement(product, quantity, StockChangeReason.RESTOCK);
        stockRepository.save(stock);
    }

    @Override
    public void decreaseStock(UUID productId, Integer quantity) {
        if (quantity <= 0) {
            throw new ArgumentInvalidException("Quantity must be greater than zero", HttpStatus.BAD_REQUEST);
        }
        Product product = getProductOrThrow(productId);
        Stock stock = product.getStock();
        if (stock.getQuantity() < quantity) {
            throw new ArgumentInvalidException("Insufficient stock", HttpStatus.BAD_REQUEST);
        }
        stock.setQuantity(stock.getQuantity() - quantity);
        stockMovementService.registerMovement(product, quantity, StockChangeReason.INVENTORY_ADJUSTMENT);
        stockRepository.save(stock);
    }

    private Product getProductOrThrow(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ArgumentInvalidException("Product not found", HttpStatus.NOT_FOUND));
    }

    private Product changeNonNullFields(Product product, ProductDTO productDto) {
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getPromotionalPrice() != null) {
            product.setPromotionalPrice(productDto.getPromotionalPrice());
        }
        return product;
    }

}

// private String name; T
// private String description; T
// private String images; N
// private BigDecimal price; T
// private String brand; N
// private BigDecimal promotionalPrice; T
// @ManyToOne
// @JoinColumn(name = "category_id")
// private Category category;