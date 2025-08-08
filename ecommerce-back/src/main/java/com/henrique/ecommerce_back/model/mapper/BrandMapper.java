package com.henrique.ecommerce_back.model.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.henrique.ecommerce_back.model.dto.BrandDTO;
import com.henrique.ecommerce_back.model.entity.Brand;

@Component
public class BrandMapper {

    @Value("${default-image}")
    private String defaultImage;

    public Brand dtoToEntity(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        brand.setLogo(dto.getLogo() != null && dto.getLogo().contains("default") ? null : dto.getLogo());
        brand.setId(dto.getId());

        return brand;
    }

    public BrandDTO entityToDTO(Brand brand) {
        BrandDTO brandDto = new BrandDTO();
        brandDto.setName(brand.getName());
        brandDto.setLogo(brand.getLogo() == null || brand.getLogo().isBlank() ? "/brand-images/" + defaultImage
                : "/brand-images/" + brand.getLogo());
        brandDto.setId(brand.getId());

        return brandDto;
    }
}
