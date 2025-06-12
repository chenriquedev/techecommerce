package com.henrique.ecommerce_back.repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.henrique.ecommerce_back.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
        @Override
        @EntityGraph(attributePaths = "category")
        Page<Product> findAll(Specification<Product> specs, Pageable page);

        Optional<Product> findById(UUID id);

        @Query(value = """
                        SELECT p FROM Product p
                        WHERE (:namePattern IS NULL OR LOWER(p.name) LIKE :namePattern)
                             AND (:category IS NULL OR p.category.name = :category)
                             AND(:priceMin IS NULL OR p.price > :priceMin)
                             AND(:priceMax IS NULL OR p.price < :priceMax)
                             AND(:brand IS NULL OR p.brand = :brand)
                        """)
        Page<Product> filterProducts(@Param("namePattern") String namePattern,
                        @Param("category") String category,
                        @Param("priceMin") BigDecimal priceMin,
                        @Param("priceMax") BigDecimal priceMax,
                        @Param("brand") String brand,
                        Pageable pageable);
}
