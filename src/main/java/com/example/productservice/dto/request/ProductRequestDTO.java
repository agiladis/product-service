package com.example.productservice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Category ID is required")
    private UUID categoryId;

    @Valid // validasi nested DTO
    @NotEmpty(message = "At least one variant is required")
    private List<@Valid  ProductVariantRequestDTO> variants;
}