package com.example.productservice.service;

import com.example.productservice.dto.request.ProductRequestDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category with ID " + dto.getCategoryId() + " not found"
                ));

        Product product = productMapper.toEntity(dto, category);

        Product savedProduct = productRepo.save(product);

        return productMapper.toResponseDTO(savedProduct);
    }

}
