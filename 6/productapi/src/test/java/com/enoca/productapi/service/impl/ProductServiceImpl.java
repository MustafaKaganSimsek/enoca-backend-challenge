package com.enoca.productapi.service.impl;

import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.enoca.productapi.entity.Category;
import com.enoca.productapi.entity.Product;
import com.enoca.productapi.repository.CategoryRepository;
import com.enoca.productapi.repository.ProductRepository;
import com.enoca.productapi.service.CategoryService;
import com.enoca.productapi.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceImpl {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductRepository productRepository;

    private UUID productId= UUID.randomUUID();
    private UUID categoryId= UUID.randomUUID();


    Product product = Product.builder()
    .id(productId)
    .name("name")
    .stock(10)
    .price(100.0)
    .description("description")
    .category(Category        
        .builder()
        .id(categoryId)
        .build())
    .build();

    @BeforeEach
    void setUp(){
        productRepository = mock(ProductRepository.class);
        
    }

    @Test
    void saveProduct_shouldReturnProduct(){
       
    }
}
