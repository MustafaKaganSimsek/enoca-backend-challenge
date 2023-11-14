package com.enoca.productapi.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class ProductRequest {

    @NotBlank
    private String name;
    private int stock;
    private double price;
    private String description;  
    private UUID categoryId;
}
