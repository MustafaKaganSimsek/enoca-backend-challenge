package com.enoca.productapi.service;
import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Category;
import com.enoca.productapi.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product saveProduct(ProductRequest productRequest);

    Product findProductById(UUID id);

    List<Product> findAllProduct();

    List<Product> findAllProductByCategory(UUID categoryId);
    void deleteProductById(UUID id);
}
