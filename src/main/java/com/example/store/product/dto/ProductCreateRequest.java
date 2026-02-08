package com.example.store.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(
        @NotNull Long sellerId,
        @NotBlank String name,
        String description,
        @Min(0) long price,
        @Min(0) int stock
) {}
