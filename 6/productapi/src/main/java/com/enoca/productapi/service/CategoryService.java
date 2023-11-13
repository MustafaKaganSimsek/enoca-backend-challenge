package com.enoca.productapi.service;

import com.enoca.productapi.dto.ProductDto;
import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Product;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    ProductDto saveProduct(ProductRequest productRequest);

    ProductDto findProductById(UUID id);

    List<ProductDto> findAllProduct();

    void deleteProductById(UUID id);
}
