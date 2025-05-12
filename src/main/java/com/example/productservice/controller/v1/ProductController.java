package com.example.productservice.controller.v1;

import com.example.productservice.dto.request.ProductRequestDTO;
import com.example.productservice.dto.response.ApiResponse;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/dashboard/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> create(@Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO response = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<ProductResponseDTO>builder()
                        .status("success")
                        .message("Product created successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAll() {
        List<ProductResponseDTO> products = productService.getAllProduct();

        return ResponseEntity.ok(
                ApiResponse.<List<ProductResponseDTO>>builder()
                        .status("success")
                        .message("Products fetched successfully")
                        .data(products)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getById(@PathVariable UUID id) {
        ProductResponseDTO product = productService.getProductById(id);

        return ResponseEntity.ok(
                ApiResponse.<ProductResponseDTO>builder()
                        .status("success")
                        .message("Product fetched successfully")
                        .data(product)
                        .build()
        );
    }
}
