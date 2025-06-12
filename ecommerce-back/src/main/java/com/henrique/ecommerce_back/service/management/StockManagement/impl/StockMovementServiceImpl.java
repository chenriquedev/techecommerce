package com.henrique.ecommerce_back.service.management.StockManagement.impl;

import org.springframework.stereotype.Service;

import com.henrique.ecommerce_back.model.entity.Product;
import com.henrique.ecommerce_back.model.entity.StockMovement;
import com.henrique.ecommerce_back.model.enums.StockChangeReason;
import com.henrique.ecommerce_back.repository.StockMovementRepository;
import com.henrique.ecommerce_back.service.management.StockManagement.StockMovementService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {
    private final StockMovementRepository stockManagementRepository;

    @Override
    public void registerMovement(Product product, Integer quantity, StockChangeReason reason) {
        StockMovement stockMovement = new StockMovement();
        stockMovement.setQuantityChanged(quantity);
        stockMovement.setReason(reason);
        stockMovement.setMovementAt(java.time.LocalDateTime.now());
        stockMovement.setProduct(product);
        stockManagementRepository.save(stockMovement);
    }
}