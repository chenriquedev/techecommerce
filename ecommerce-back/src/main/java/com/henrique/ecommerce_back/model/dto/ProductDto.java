package com.henrique.ecommerce_back.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private String images;
    private BigDecimal price;
    private Integer pixDiscount;
    private Integer quantity;
    private Integer salesQuantity;
    private Boolean onSale;
    private BigDecimal promotionalPrice;
    private CategoryDto category;
}
