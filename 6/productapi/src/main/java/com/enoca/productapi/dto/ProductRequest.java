package com.enoca.productapi.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductRequest {
    private String productName;
    private int stock;
    private double price;
    private String description;  
    private UUID categoryId;
}
