package com.enoca.productapi.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is madotory")
    private String name;
    private int stock;
    private double price;
    private String description;  
    private UUID categoryId;
}
