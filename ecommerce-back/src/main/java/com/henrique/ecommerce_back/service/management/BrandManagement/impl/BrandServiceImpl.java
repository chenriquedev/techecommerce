package com.henrique.ecommerce_back.service.management.BrandManagement.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.exceptions.BrandAlreadyExistsException;
import com.henrique.ecommerce_back.exceptions.BrandImageNotFoundException;
import com.henrique.ecommerce_back.exceptions.BrandNotFoundException;
import com.henrique.ecommerce_back.exceptions.StorageException;
import com.henrique.ecommerce_back.model.dto.BrandDTO;
import com.henrique.ecommerce_back.model.entity.Brand;
import com.henrique.ecommerce_back.model.mapper.BrandMapper;
import com.henrique.ecommerce_back.repository.BrandRepository;
import com.henrique.ecommerce_back.service.management.BrandManagement.BrandService;
import com.henrique.ecommerce_back.utils.AnalyseImageType;
import com.henrique.ecommerce_back.utils.ImageFileManager;
import com.henrique.ecommerce_back.utils.ImageFileManagerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    @Value("${brand.image.path}")
    private String brandImagePath;

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final AnalyseImageType analyseImageType;
    private final ImageFileManagerFactory imageFileManagerFactory;

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream().map(brandMapper::entityToDTO).toList();
    }

    @Override
    public BrandDTO newBrand(BrandDTO brandDTO) {
        Optional<Brand> brandExists = brandRepository.findByNameIgnoreCase(brandDTO.getName().toLowerCase().trim());
        if (brandExists.isPresent()) {
            throw new BrandAlreadyExistsException(
                    String.format("Brand %s already exists", brandExists.get().getName()));
        }

        Brand newBrand = brandRepository.save(brandMapper.dtoToEntity(brandDTO));

        return brandMapper.entityToDTO(newBrand);
    }

    @Override
    public void deleteBrand(UUID id) {
        if (!brandRepository.existsById(id)) {
            throw new BrandNotFoundException("Brand doesn't exists.");
        }
        brandRepository.deleteById(id);
    }

    @Override
    public void addBrandImage(UUID brandID, MultipartFile file) {
        ImageFileManager manager = imageFileManagerFactory.create(brandImagePath);
        Brand brand = getBrandOrThrow(brandID);
        String oldImagePath = null;

        analyseImageType.validateImageType(file.getContentType());

        try {
            if (brand.getLogo() != null && !brand.getLogo().isBlank()) {
                oldImagePath = brand.getLogo();
            }
            String fileName = manager.storeImage(file);
            brand.setLogo(fileName);
            brandRepository.saveAndFlush(brand);
            if (oldImagePath != null) {
                manager.deleteImageFile(oldImagePath);
            }
        } catch (Exception e) {
            throw new StorageException("Error to save image");
        }
    }

    @Override
    public void removeBrandImage(UUID brandID, String imageName) {
        ImageFileManager manager = imageFileManagerFactory.create(brandImagePath);
        Brand brand = getBrandOrThrow(brandID);
        if (brand.getLogo() == null || !brand.getLogo().equals(imageName)) {
            throw new BrandImageNotFoundException("Brand image doesn't exist");
        }
        brand.setLogo(null);
        brandRepository.saveAndFlush(brand);
        manager.deleteImageFile(imageName);
    }

    private Brand getBrandOrThrow(UUID BrandID) {
        return brandRepository.findById(BrandID)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));
    }
}
