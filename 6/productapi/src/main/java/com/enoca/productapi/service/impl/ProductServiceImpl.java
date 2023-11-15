package com.enoca.productapi.service.impl;

import java.util.List;
import java.util.UUID;

import com.enoca.productapi.dto.UpdateProductRequest;
import com.enoca.productapi.exception.ProductNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.enoca.productapi.dto.CreateProductRequest;
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
    public Product saveProduct(CreateProductRequest createProductRequest) {
        log.debug("Request to create new product : {}", createProductRequest);

        return productRepository.save(Product.builder()
                    .name(createProductRequest.getName())
                    .price(createProductRequest.getPrice())
                    .stock(createProductRequest.getStock())
                    .category(categoryService.findCategoryById(createProductRequest.getCategoryId()))
                    .description(createProductRequest.getDescription())
                    .build()
        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest updateProductRequest, UUID id){
        log.debug("Request to update product : {}", updateProductRequest);


        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product "+id+" Not Found"));

            if (updateProductRequest.getName()!=null&&!updateProductRequest.getName().isBlank()){
                product.setName(updateProductRequest.getName());
            }
            if (updateProductRequest.getStock()!=null) {
                product.setStock(updateProductRequest.getStock());
            }
            if (updateProductRequest.getPrice()!=null) {
                product.setPrice(updateProductRequest.getPrice());
            }
            if(updateProductRequest.getDescription()!=null) {
                product.setDescription(updateProductRequest.getDescription());
            }
            if (updateProductRequest.getCategoryId()!=null) {
                product.setCategory(categoryService.findCategoryById(updateProductRequest.getCategoryId()));
            }

            return productRepository.save(product);
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

        return productRepository.findAllByCategory(categoryService.findCategoryById(categoryId));
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
