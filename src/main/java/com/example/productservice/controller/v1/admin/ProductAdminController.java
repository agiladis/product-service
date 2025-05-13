package com.example.productservice.controller.v1.admin;

import com.example.productservice.dto.request.ProductRequestDTO;
import com.example.productservice.dto.response.ApiResponse;
import com.example.productservice.dto.response.ProductResponseDTO;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/private/products")
@AllArgsConstructor
public class ProductAdminController {

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> update(
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequestDTO request
    ) {

        ProductResponseDTO response = productService.updateProduct(id, request);

        return ResponseEntity.ok(
                ApiResponse.<ProductResponseDTO>builder()
                        .status("success")
                        .message("product updated successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable UUID id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status("success")
                        .message("Product deleted successfully")
                        .data(null)
                        .build()
        );
    }
}
