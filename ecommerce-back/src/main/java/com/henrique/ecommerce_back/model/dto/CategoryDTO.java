package com.henrique.ecommerce_back.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CategoryDTO {
    private UUID id;
    private String name;
}
