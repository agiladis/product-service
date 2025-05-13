package com.example.productservice.service.impl;

import com.example.productservice.constant.ProductStatus;
import com.example.productservice.dto.request.ProductRequestDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.exception.ResourceNotFoundException;

import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductVariant;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = productMapper.toEntity(dto);
        product.setCategory(category);
        product.setStatus(ProductStatus.DRAFT);

        List<ProductVariant> variants = dto.getVariants().stream().map(v -> {
            ProductVariant variant = new ProductVariant();
            variant.setSku(v.getSku());
            variant.setSize(v.getSize());
            variant.setColor(v.getColor());
            variant.setPrice(v.getPrice());
            variant.setProduct(product);
            return variant;
        }).toList();

        product.setVariants(variants);

        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    @Override
    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        return productMapper.toDto(product);
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // Update product fields
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategory(category);

        // Clear past variant
        product.getVariants().clear();
        List<ProductVariant> newVariants = dto.getVariants().stream().map(v -> {
            ProductVariant variant = new ProductVariant();
            variant.setSku(v.getSku());
            variant.setSize(v.getSize());
            variant.setColor(v.getColor());
            variant.setPrice(v.getPrice());
            variant.setProduct(product);
            return variant;
        }).toList();
        product.getVariants().addAll(newVariants);

        Product updated = productRepository.save(product);
        return productMapper.toDto(updated);
    }
}
