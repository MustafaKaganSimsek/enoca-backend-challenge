package com.enoca.productapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode
@Builder
public class UpdateProductRequest {
    private String name;
    private Integer stock;
    private Double price;
    private String description;
    private UUID categoryId;
}
