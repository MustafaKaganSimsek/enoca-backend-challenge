package com.enoca.productapi.dto.converter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.enoca.productapi.dto.CategoryDto;
import com.enoca.productapi.dto.ProductDto;
import com.enoca.productapi.entity.Product;

@Component
public class ProductDtoConverter {
    
    public ProductDto productToDto(Product product){
        return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())  
        .stock(product.getStock())
        .price(product.getPrice())  
        .description(product.getDescription())
        .category(CategoryDto.builder()
                    .id(product.getCategory().getId())
                    .name(product.getCategory().getName())
                    .createdBy(product.getCategory().getCreatedBy())
                    .createdDate(product.getCategory().getCreatedDate())
                    .lastModifiedBy(product.getCategory().getLastModifiedBy())
                    .lastModifiedDate(product.getCategory().getLastModifiedDate())
                    .build()
                    )
        .createdBy(product.getCreatedBy())
        .createdDate(product.getCreatedDate())
        .lastModifiedBy(product.getLastModifiedBy())
        .lastModifiedDate(product.getLastModifiedDate())
        .build()
        ;
    }
    public List<ProductDto> productToDto(List<Product> productList){
        return productList.stream().map(product-> productToDto(product)).toList();
    }
}
