package com.henrique.ecommerce_back.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.henrique.ecommerce_back.model.entity.Product;

public class ProductSpecifications {
    public static Specification<Product> activeProducts(Boolean isActive) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), isActive);
    }
}
