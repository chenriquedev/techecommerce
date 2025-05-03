package com.henrique.ecommerce_back.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CategoryDto {
    private UUID id;
    private String name;
}
