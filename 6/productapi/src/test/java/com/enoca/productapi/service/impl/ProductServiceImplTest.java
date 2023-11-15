package com.enoca.productapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.enoca.productapi.dto.CreateProductRequest;
import com.enoca.productapi.dto.UpdateProductRequest;
import com.enoca.productapi.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.enoca.productapi.entity.Category;
import com.enoca.productapi.entity.Product;
import com.enoca.productapi.repository.ProductRepository;
import com.enoca.productapi.service.CategoryService;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private UUID productId = UUID.randomUUID();
    private UUID categoryId = UUID.randomUUID();


    Category category = Category.builder()
            .id(categoryId)
            .name("name")
            .build();

    Product product = Product.builder()
            .id(productId)
            .name("name")
            .stock(10)
            .price(100.0)
            .description("description")
            .category(category)
            .build();

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        categoryService = mock(CategoryServiceImpl.class);
        productService = new ProductServiceImpl(productRepository, categoryService);
    }

    @Test
    void saveProduct_shouldReturnProduct() {
        Product testProduct = Product.builder()
                .name("name")
                .stock(10)
                .price(100.0)
                .description("description")
                .category(category)
                .build();
        CreateProductRequest createProductRequest = CreateProductRequest.builder()
                .name("name")
                .stock(10)
                .price(100.0)
                .description("description")
                .categoryId(categoryId)
                .build();
        when(categoryService.findCategoryById(categoryId)).thenReturn(category);
        when(productRepository.save(testProduct)).thenReturn(product);
        Product result = productService.saveProduct(createProductRequest);
        assertEquals(product, result);

    }

    @Test
    void updateProduct_shouldReturnProduct() {
        Category category1 = Category.builder()
                .id(UUID.randomUUID())
                .name("another name")
                .build();

        Product updatedProduct = Product.builder()
                .id(productId)
                .name("updated name")
                .stock(5)
                .price(50.0)
                .description("updated description")
                .category(category1)
                .build();

        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                .name("updated name")
                .stock(5)
                .price(50.0)
                .description("updated description")
                .categoryId(category1.getId())
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(categoryService.findCategoryById(category1.getId())).thenReturn(category1);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(updateProductRequest, productId);

        assertEquals(updatedProduct, result);
    }

    @Test
    void findProductById_shouldReturnProduct() {
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(product));
        Product result = productService.findProductById(productId);

        assertEquals(product, result);
    }

    @Test
    void findAll_shouldReturnProductList() {
        Product testProduct = Product.builder()
                .id(UUID.randomUUID())
                .name("name1")
                .stock(5)
                .price(10.0)
                .description("description1")
                .category(category)
                .build();
        List<Product> productList = List.of(testProduct, product);
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> resultList = productService.findAllProduct();

        assertEquals(productList, resultList);
    }

    @Test
    void findAllProductByCategory_shouldReturnProductList() {
        Product testProduct = Product.builder()
                .id(UUID.randomUUID())
                .name("name1")
                .stock(5)
                .price(10.0)
                .description("description1")
                .category(category)
                .build();
        List<Product> productList = List.of(testProduct, product);

        when(categoryService.findCategoryById(categoryId)).thenReturn(category);
        when(productRepository.findAllByCategory(category)).thenReturn(productList);
        List<Product> resultList = productService.findAllProductByCategory(categoryId);

        assertEquals(productList, resultList);
    }

    @Test
    void findProductById_throwsProductNotFoundException() {
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(null));

        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(productId));
    }

    @Test
    void updateProduct_throwsProductNotFoundException() {
        UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                .name("updated name")
                .stock(5)
                .price(50.0)
                .description("updated description")
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(null));

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(updateProductRequest, productId));

    }
}