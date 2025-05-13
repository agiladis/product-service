package com.example.productservice.service;

import com.example.productservice.dto.request.ProductRequestDTO;
import com.example.productservice.dto.response.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDTO createProduct(ProductRequestDTO dto);
    List<ProductResponseDTO> getAllProduct();
    ProductResponseDTO getProductById(UUID id);
    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto);
    void deleteProduct(UUID id);
}
