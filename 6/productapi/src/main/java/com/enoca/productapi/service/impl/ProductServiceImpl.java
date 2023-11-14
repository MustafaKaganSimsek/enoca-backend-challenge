package com.enoca.productapi.service.impl;

import java.util.List;
import java.util.UUID;

import com.enoca.productapi.entity.Category;
import com.enoca.productapi.exception.CategoryNotFoundException;
import com.enoca.productapi.exception.ProductNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Product;
import com.enoca.productapi.repository.ProductRepository;
import com.enoca.productapi.service.CategoryService;
import com.enoca.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    @Override
    public Product saveProduct(ProductRequest productRequest) {
        log.debug("Request to create new product : {}",productRequest);

        return productRepository.save(Product.builder()
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .stock(productRequest.getStock())
                    .category(categoryService.findCategoyById(productRequest.getCategoryId()))
                    .description(productRequest.getDescription())
                    .build()
        );
    }

    @Override
    public Product findProductById(UUID id) {
        log.debug("Request to find by id new product : {}",id);
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product "+id+" Not Found"));
    }

    @Override
    public List<Product> findAllProduct() {
        log.debug("Find All Products");
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllProductByCategory(UUID categoryId) {
        log.debug("Find All Products By Category");

        return productRepository.findAllByCategory(categoryService.findCategoyById(categoryId));
    }

    @Override
    public void deleteProductById(UUID id) {
        log.debug("Delete Product {}",id);
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException("Product "+id+" Not found");
        }
    }
    
    
}
