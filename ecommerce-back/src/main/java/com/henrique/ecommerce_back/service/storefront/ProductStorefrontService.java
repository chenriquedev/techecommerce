package com.henrique.ecommerce_back.service.storefront;


import com.henrique.ecommerce_back.model.dto.FilterProductDto;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDto;
import com.henrique.ecommerce_back.model.dto.ProductDto;

public interface ProductStorefrontService {
    PaginatedResponseDto<ProductDto> getAllProducts(PageDTO page);

    ProductDto getProductById(String Id);

    PaginatedResponseDto<ProductDto> filterProducts(FilterProductDto filterProductDto, PageDTO pageDTO);
}
