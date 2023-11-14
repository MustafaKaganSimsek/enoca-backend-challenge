package com.enoca.productapi.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Product;
import com.enoca.productapi.repository.ProductRepository;
import com.enoca.productapi.service.CategoryService;
import com.enoca.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    @Override
    public Product saveProduct(ProductRequest productRequest) {
       return productRepository.save(Product.builder()
                                    .productName(productRequest.getProductName())
                                    .price(productRequest.getPrice())
                                    .stock(productRequest.getStock())
                                    .category(categoryService.findCategoyById(productRequest.getCategoryId()))
                                    .description(productRequest.getDescription())
                                    .build()
                                    );
    }

    @Override
    public Product findProductById(UUID id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);;
    }
    
    
}
