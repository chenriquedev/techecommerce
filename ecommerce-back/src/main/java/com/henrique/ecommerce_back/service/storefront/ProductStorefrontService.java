package com.henrique.ecommerce_back.service.storefront;


import java.util.UUID;

import com.henrique.ecommerce_back.model.dto.FilterProductDTO;
import com.henrique.ecommerce_back.model.dto.PageDTO;
import com.henrique.ecommerce_back.model.dto.PaginatedResponseDTO;
import com.henrique.ecommerce_back.model.dto.ProductDTO;

public interface ProductStorefrontService {
    PaginatedResponseDTO<ProductDTO> getAllProducts(PageDTO page, Boolean isActive);

    ProductDTO getProductById(UUID Id);

    PaginatedResponseDTO<ProductDTO> filterProducts(FilterProductDTO filterProductDto, PageDTO pageDTO);
}
