package com.henrique.ecommerce_back.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDto<T> {
    private List<T> content;
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalItems;
}
