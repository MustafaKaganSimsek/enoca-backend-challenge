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

import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Product;
import com.enoca.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
     private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @GetMapping("/find")
    public ResponseEntity<Product> findProductById(@RequestParam UUID id){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Product>> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @DeleteMapping("/delete")
    public void deleteProductById(@RequestParam UUID id){
        productService.deleteProductById(id);
    }
}
