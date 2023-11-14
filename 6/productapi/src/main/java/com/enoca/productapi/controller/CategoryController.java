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

import com.enoca.productapi.dto.CategoryRequest;
import com.enoca.productapi.entity.Category;
import com.enoca.productapi.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        log.debug("REST Request to save all Category");

        return ResponseEntity.ok(categoryService.saveCategory(categoryRequest));
    }

    @GetMapping("/find")
    public ResponseEntity<Category> findCategoryById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to find Category by Id");

        return ResponseEntity.ok(categoryService.findCategoyById(id));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Category>> findAllCategory(){
        log.debug("REST Request to find all Categories");

        return ResponseEntity.ok(categoryService.findAllCategory());
    }

    @DeleteMapping("/delete")
    public void deleteCategoryById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to delete Category by Id");

        categoryService.deleteCategoryById(id);
    }
}


