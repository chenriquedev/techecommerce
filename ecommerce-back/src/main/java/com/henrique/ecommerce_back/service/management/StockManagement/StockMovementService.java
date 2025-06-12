package com.henrique.ecommerce_back.service.management.StockManagement;

import com.henrique.ecommerce_back.model.entity.Product;
import com.henrique.ecommerce_back.model.enums.StockChangeReason;

public interface StockMovementService {
    void registerMovement(Product product, Integer quantity, StockChangeReason reason);
}
