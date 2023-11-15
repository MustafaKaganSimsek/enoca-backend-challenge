package com.enoca.productapi.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enoca.productapi.dto.CategoryDto;
import com.enoca.productapi.dto.CategoryRequest;
import com.enoca.productapi.dto.converter.CategoryDtoConverter;
import com.enoca.productapi.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {
    
    private final CategoryService categoryService;

    private final CategoryDtoConverter categoryDtoConverter;

    @PostMapping("/save")
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        log.debug("REST Request to save all Category");
        return ResponseEntity.ok(categoryDtoConverter.categoryToDto(categoryService.saveCategory(categoryRequest)));
    }

    @PostMapping("/update/name")
    public ResponseEntity<CategoryDto> updateCategory(@NotNull @RequestParam UUID id,@NotNull @RequestParam String name){
        log.debug("REST Request to update Category's name");
        return ResponseEntity.ok(categoryDtoConverter.categoryToDto(categoryService.updateCategoryName(name,id)));
    }

    @GetMapping("/find")
    public ResponseEntity<CategoryDto> findCategoryById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to find Category by Id");

        return ResponseEntity.ok(categoryDtoConverter.categoryToDto(categoryService.findCategoyById(id)));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CategoryDto>> findAllCategory(){
        log.debug("REST Request to find all Categories");

        return ResponseEntity.ok(categoryDtoConverter.categorToDto(categoryService.findAllCategory()));
    }

    @DeleteMapping("/delete")
    public void deleteCategoryById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to delete Category by Id");

        categoryService.deleteCategoryById(id);
    }
}


