package com.henrique.ecommerce_back.service.management.BrandManagement.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.henrique.ecommerce_back.exceptions.BrandAlreadyExistsException;
import com.henrique.ecommerce_back.exceptions.BrandNotFoundException;
import com.henrique.ecommerce_back.exceptions.StorageException;
import com.henrique.ecommerce_back.model.dto.BrandDTO;
import com.henrique.ecommerce_back.model.entity.Brand;
import com.henrique.ecommerce_back.model.mapper.BrandMapper;
import com.henrique.ecommerce_back.repository.BrandRepository;
import com.henrique.ecommerce_back.service.management.BrandManagement.BrandService;
import com.henrique.ecommerce_back.utils.AnalyseImageType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    @Value("${brand.image.path}")
    private String brandImagePath;

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final AnalyseImageType analyseImageType;

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
        Brand brand = getBrandOrThrow(brandID);
        String oldImagePath = null;

        analyseImageType.validateImageType(file.getContentType());

        try {
            if (brand.getLogo() != null && !brand.getLogo().isBlank()) {
                oldImagePath = brand.getLogo();
            }
            String fileName = storeBrandImage(file);
            brand.setLogo(fileName);
            brandRepository.saveAndFlush(brand);
            if (oldImagePath != null) {
                deleteBrandImageFile(oldImagePath);
            }
        } catch (Exception e) {
            throw new StorageException("Error to save image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void removeBrandImage(UUID brandID, String imageName) {
        Brand brand = getBrandOrThrow(brandID);
        if (brand.getLogo() == null || !brand.getLogo().equals(imageName)) {
            throw new StorageException("Error to delete brand image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        brand.setLogo(null);
        brandRepository.saveAndFlush(brand);
        deleteBrandImageFile(imageName);
    }

    private Brand getBrandOrThrow(UUID BrandID) {
        return brandRepository.findById(BrandID)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found"));
    }

    private String storeBrandImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(brandImagePath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            file.transferTo(path.resolve(fileName));
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Error saving image '" + fileName + "'", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void deleteBrandImageFile(String imageName) {
        Path path = Paths.get(brandImagePath + "/" + imageName);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("Error to delete image '" + imageName + "'" + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
