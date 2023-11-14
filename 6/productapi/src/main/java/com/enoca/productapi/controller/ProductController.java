package com.enoca.productapi.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.entity.Product;
import com.enoca.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
     private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@Valid  @RequestBody ProductRequest productRequest){
        log.debug("REST Request to save all Product");

        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @GetMapping("/find")
    public ResponseEntity<Product> findProductById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to find Product by Id");

        return ResponseEntity.ok(productService.findProductById(id));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Product>> findAllProduct(){
        log.debug("REST Request to find all Products");

        return ResponseEntity.ok(productService.findAllProduct());
    }

    @GetMapping("/find/all/category/{id}")
    public ResponseEntity<List<Product>> findAllProductByCatagory(@NotNull @RequestParam(name = "id") UUID categoryId){
        log.debug("REST Request to find all Products by Category");

        return ResponseEntity.ok(productService.findAllProductByCategory(categoryId));
    }

    @DeleteMapping("/delete")
    public void deleteProductById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to delete Product by Id");

        productService.deleteProductById(id);
    }
}
