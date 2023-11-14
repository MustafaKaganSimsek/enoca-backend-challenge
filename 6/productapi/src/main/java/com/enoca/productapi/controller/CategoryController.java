package com.enoca.productapi.controller;

import java.util.List;
import java.util.UUID;

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


@RequiredArgsConstructor
@RestController
@RequestMapping("category")
public class CategoryController {
    
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.saveCategory(categoryRequest));
    }

    @GetMapping("/find")
    public ResponseEntity<Category> findCategoryById(@RequestParam UUID id){
        return ResponseEntity.ok(categoryService.findCategoyById(id));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Category>> findAllCategory(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

    @DeleteMapping("/delete")
    public void deleteCategoryById(@RequestParam UUID id){
        categoryService.deleteCategoryById(id);
    }
}


