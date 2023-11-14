package com.enoca.productapi.service;
import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product saveProduct(ProductRequest productRequest);

    Product findProductById(UUID id);

    List<Product> findAllProduct();

    void deleteProductById(UUID id);
}
