package com.henrique.ecommerce_back.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henrique.ecommerce_back.model.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByNameIgnoreCase(String name);
    Optional<Brand> findById(UUID id);
}
