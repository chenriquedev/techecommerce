package com.henrique.ecommerce_back.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.henrique.ecommerce_back.model.enums.StockChangeReason;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Min(value = 0, message = "Quantity changed must be a non-negative integer.")
    private Integer quantityChanged;
    private LocalDateTime movementAt;
    @Enumerated(EnumType.STRING)
    private StockChangeReason reason;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
