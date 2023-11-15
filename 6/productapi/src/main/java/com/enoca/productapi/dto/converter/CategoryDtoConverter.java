package com.enoca.productapi.dto.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.enoca.productapi.dto.CategoryDto;
import com.enoca.productapi.entity.Category;

@Component
public class CategoryDtoConverter {

    
    public CategoryDto categoryToDto(Category category){
        
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .createdBy(category.getCreatedBy())
                .createdDate(category.getCreatedDate())
                .lastModifiedBy(category.getLastModifiedBy())
                .lastModifiedDate(category.getLastModifiedDate())
                .build();
        
    }
    public List<CategoryDto> categorToDto(List<Category> categoryList){
        return categoryList.stream().map(category-> categoryToDto(category)).toList();
    }
}
