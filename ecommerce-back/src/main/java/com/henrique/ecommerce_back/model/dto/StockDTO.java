package com.henrique.ecommerce_back.model.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StockDTO {
    private UUID id;
    @Min(value = 0, message = "Value can't be less than zero.")
    private Integer quantity;

}
