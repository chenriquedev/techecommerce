package com.henrique.ecommerce_back.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.ecommerce_back.model.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, UUID> {
	
}
