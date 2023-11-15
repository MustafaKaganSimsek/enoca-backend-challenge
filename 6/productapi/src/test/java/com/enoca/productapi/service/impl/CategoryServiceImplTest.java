package com.enoca.productapi.service.impl;

import com.enoca.productapi.dto.CategoryRequest;
import com.enoca.productapi.entity.Category;
import com.enoca.productapi.exception.CategoryNotFoundException;
import com.enoca.productapi.exception.ExistsCategoryException;
import com.enoca.productapi.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;

    private UUID categoryId= UUID.randomUUID();

    Category category = Category.builder()
            .id(categoryId)
            .name("name")
            .build();

    @BeforeEach
    void setup(){
        categoryRepository = mock(CategoryRepository.class);
        categoryService= new CategoryServiceImpl(categoryRepository);

    }

    @Test
    void saveCategory_shouldReturnCategory(){
        Category testCategory= Category.builder()
                .name("name")
                .build();
        CategoryRequest categoryRequest = CategoryRequest.builder()
                .name("name")
                .build();

        when(categoryRepository.existsByName(categoryRequest.getName())).thenReturn(false);
        when(categoryRepository.save(testCategory)).thenReturn(category);
        Category result = categoryService.saveCategory(categoryRequest);

        assertEquals(category,result);
    }

    @Test
    void updateCategoryName_shouldReturnUpdatedCategory(){
        Category updatedCategory= Category.builder()
                .id(categoryId)
                .name("updatedName")
                .build();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(category));
        when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);
        Category result = categoryService.updateCategoryName(updatedCategory.getName(), categoryId);

        assertEquals(updatedCategory,result);


    }

    @Test
    void findCategoyById_shouldReturnCategory(){
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(category));

        Category result = categoryService.findCategoryById(categoryId);

        assertEquals(category,result);
    }

    @Test
    void findAllCategory_shouldReturnCategories(){
        Category category1 = Category.builder()
                .id(UUID.randomUUID())
                .name("name1")
                .build();

        List<Category> categoryList = List.of(category1,category);

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<Category> resultList = categoryService.findAllCategory();

        assertEquals(categoryList,resultList);
    }

    @Test
    void saveCategory_throwExistsCategoryException(){
        Category testCategory= Category.builder()
                .name("name")
                .build();
        CategoryRequest categoryRequest = CategoryRequest.builder()
                .name("name")
                .build();

        when(categoryRepository.existsByName(categoryRequest.getName())).thenReturn(true);

        assertThrows(ExistsCategoryException.class,()->categoryService.saveCategory(categoryRequest));
    }

    @Test
    void updateCategoryName_throwsCategoryNotFoundException(){
        Category updatedCategory= Category.builder()
                .id(categoryId)
                .name("updatedName")
                .build();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(null));

        assertThrows(CategoryNotFoundException.class,()->categoryService.updateCategoryName(updatedCategory.getName(),categoryId));


    }
    @Test
    void findCategoyById_throwsCategoryNotFoundException(){
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(null));


        assertThrows(CategoryNotFoundException.class,()->categoryService.findCategoryById(categoryId));
    }
}
