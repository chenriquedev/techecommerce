package com.henrique.ecommerce_back.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.henrique.ecommerce_back.model.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
     Optional<Category> findByNameIgnoreCase(String name);
      Optional<Category> findById(UUID id);
}
