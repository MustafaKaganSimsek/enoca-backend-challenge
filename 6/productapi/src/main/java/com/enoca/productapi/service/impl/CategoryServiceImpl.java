package com.enoca.productapi.service.impl;

import java.util.List;
import java.util.UUID;

import com.enoca.productapi.exception.CategoryNotFoundException;
import com.enoca.productapi.exception.ExistsCategoryException;
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

        if (categoryRepository.existsByName(categoryRequest.getName().toLowerCase())){

            throw new ExistsCategoryException("Category Is Exist "+categoryRequest.getName());

        }else {
            return categoryRepository.save(Category.builder()
                    .name(categoryRequest.getName())
                    .build());

        }

    }

    @Override
    public Category updateCategoryName(String name, UUID id) {
        log.debug("Request to update category name: {} with id:{}",name,id);

        Category category = categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("Category "+id+" Not Found"));
            
        category.setName(name);
        return categoryRepository.save(category);
        
    }

    @Override
    public Category findCategoyById(UUID id) {
        log.debug("Request to find by id new category : {}",id);
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category "+id+" Not Found"));
    }

    @Override
    public List<Category> findAllCategory() {
        log.debug("Find All Categories");
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(UUID id) {
        log.debug("Delete Category {}",id);
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        }else {
            throw new CategoryNotFoundException("Category "+id+" Not Found");
        }
    }

    
}
