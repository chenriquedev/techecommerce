package com.henrique.ecommerce_back.service.management.BrandManagement;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.model.dto.BrandDTO;

public interface BrandService {
    List<BrandDTO> getAllBrands();

    BrandDTO newBrand(BrandDTO brandDTO);

    void deleteBrand(UUID id);

    void addBrandImage(UUID brandID, MultipartFile file);

    void removeBrandImage(UUID brandID, String imageName);
}
