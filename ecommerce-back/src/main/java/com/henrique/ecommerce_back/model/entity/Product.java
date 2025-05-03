package com.henrique.ecommerce_back.model.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
