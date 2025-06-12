package com.henrique.ecommerce_back.service.storefront;


import com.henrique.ecommerce_back.model.dto.FilterProductDTO;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDTO;
import com.henrique.ecommerce_back.model.dto.ProductDTO;

public interface ProductStorefrontService {
    PaginatedResponseDTO<ProductDTO> getAllProducts(PageDTO page, Boolean isActive);

    ProductDTO getProductById(String Id);

    PaginatedResponseDTO<ProductDTO> filterProducts(FilterProductDTO filterProductDto, PageDTO pageDTO);
}
