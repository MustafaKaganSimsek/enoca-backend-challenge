package com.enoca.productapi.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class CreateProductRequest {

    @NotBlank(message = "Name is madotory")
    private String name;

    @Min(0)
    private int stock;

    @Min(0)
    private double price;
    private String description;  
    private UUID categoryId;
}
