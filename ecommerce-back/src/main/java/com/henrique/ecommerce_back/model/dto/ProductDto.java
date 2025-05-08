package com.henrique.ecommerce_back.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    private UUID id;

    @NotBlank(message = "Name can't be null.")
    private String name;
    
    private String description;
    
    private String images;
    
    private String brand;
    
    @NotNull(message = " Price can't be null.")
    @DecimalMin(value = "0.0", message = "Price must be greater than zero.")
    private BigDecimal price;
    
    @Min(value=0, message="Pix Discount can't be less than zero")
    @Max(value=100, message="Pix discount can't be greater than zero.")
    private Integer pixDiscount;
    
    @NotNull(message = "Stock must have a value.")
    @Min(value = 0, message = "Value can't be less than zero.")
    private Integer stock;
    
    @Min(value=0, message="Sales quantity can't be less than zero.")
    private Integer salesQuantity;
    
    private Boolean onSale;
    
    private BigDecimal promotionalPrice;
    
    @NotNull(message = "The product must have a category.")
    private CategoryDto category;
}
