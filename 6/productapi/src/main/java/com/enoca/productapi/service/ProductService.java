package com.enoca.productapi.service;
import com.enoca.productapi.dto.CreateProductRequest;
import com.enoca.productapi.dto.UpdateProductRequest;
import com.enoca.productapi.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product saveProduct(CreateProductRequest createProductRequest);

    Product updateProduct(UpdateProductRequest updateProductRequest,UUID id);
    Product findProductById(UUID id);

    List<Product> findAllProduct();

    List<Product> findAllProductByCategory(UUID categoryId);
    
    void deleteProductById(UUID id);
}
