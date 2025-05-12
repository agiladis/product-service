package com.example.productservice.mapper;

import com.example.productservice.dto.request.ProductRequestDTO;
import com.example.productservice.dto.request.ProductVariantRequestDTO;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductRequestDTO dto);

    ProductResponseDTO toDto(Product product);
//
//    @Mapping(target = "product", ignore = true)
//    ProductVariant toVariantEntity(ProductVariantRequestDTO dto);
}
