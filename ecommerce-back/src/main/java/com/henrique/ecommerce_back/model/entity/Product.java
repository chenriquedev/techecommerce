package com.henrique.ecommerce_back.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private Boolean isActive;
    private String name;
    private String description;
    @ElementCollection(fetch=FetchType.EAGER)
    private List<String> images;
    private BigDecimal price;
    private String brand;
    private BigDecimal promotionalPrice;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stock_id", referencedColumnName="id")
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @lombok.ToString.Exclude
    private Category category;
    // private Integer salesQuantity;
    // private Integer pixDiscount;
    // private Boolean onSale;
}
