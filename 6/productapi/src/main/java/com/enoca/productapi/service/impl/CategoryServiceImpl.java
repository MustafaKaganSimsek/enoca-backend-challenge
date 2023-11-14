package com.enoca.productapi.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.enoca.productapi.dto.CategoryRequest;
import com.enoca.productapi.entity.Category;
import com.enoca.productapi.repository.CategoryRepository;
import com.enoca.productapi.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(CategoryRequest categoryRequest) {
        log.debug("Request to create new category : {}",categoryRequest);
        return categoryRepository.save(Category.builder()
        .name(categoryRequest.getName()) 
        .build()   
        );
    }

    @Override
    public Category findCategoyById(UUID id) {
        log.debug("Request to find by id new category : {}",id);
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(UUID id) {
        categoryRepository.deleteById(id);;
    }
}
