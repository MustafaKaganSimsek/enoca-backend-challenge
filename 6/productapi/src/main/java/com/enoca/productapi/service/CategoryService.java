package com.enoca.productapi.service;

import com.enoca.productapi.dto.CategoryRequest;
import com.enoca.productapi.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category saveCategory(CategoryRequest categoryRequest);

    Category updateCategoryName(String name, UUID id);

    Category findCategoyById(UUID id);

    List<Category> findAllCategory();

    void deleteCategoryById(UUID id);
}
