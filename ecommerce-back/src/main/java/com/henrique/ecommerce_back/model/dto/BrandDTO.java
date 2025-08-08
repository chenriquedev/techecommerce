package com.henrique.ecommerce_back.model.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BrandDTO {
    private UUID id;
    @NotNull
    private String name;
    private String logo;
}
