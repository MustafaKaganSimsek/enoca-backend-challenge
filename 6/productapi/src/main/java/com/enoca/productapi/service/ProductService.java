package com.enoca.productapi.service;

import com.enoca.productapi.dto.CategoryDto;
import com.enoca.productapi.dto.CategoryRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    CategoryDto saveCategory(CategoryRequest categoryRequest);

    CategoryDto findCategoyById(UUID id);

    List<CategoryDto> findAllCategory();

    void deleteCategoryById(UUID id);
}
