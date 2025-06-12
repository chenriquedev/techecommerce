package com.henrique.ecommerce_back.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.ecommerce_back.model.entity.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID>{

}
