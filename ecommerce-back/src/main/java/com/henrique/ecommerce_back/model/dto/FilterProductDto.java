package com.henrique.ecommerce_back.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductDto {
    private String name;
    private String brand;
    private String category;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private Boolean onSale;
}
