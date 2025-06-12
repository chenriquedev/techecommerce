package com.henrique.ecommerce_back.model.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDTO<T> {
    private List<T> content;
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalItems;

    public static <T> PaginatedResponseDTO<T> fromPage(Page<T> page) {
        return new PaginatedResponseDTO<>(
                page.getContent(),
                page.getSize(),
                page.getNumber(),
                page.getTotalElements());

    }
}
