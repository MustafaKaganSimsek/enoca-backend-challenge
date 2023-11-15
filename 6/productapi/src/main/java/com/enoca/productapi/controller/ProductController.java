package com.enoca.productapi.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enoca.productapi.dto.ProductDto;
import com.enoca.productapi.dto.ProductRequest;
import com.enoca.productapi.dto.converter.ProductDtoConverter;
import com.enoca.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {
     private final ProductService productService;
     private final ProductDtoConverter productDtoConverter;

    @PostMapping("/save")
    public ResponseEntity<ProductDto> saveProduct(@Valid  @RequestBody ProductRequest productRequest){
        log.debug("REST Request to save all Product");

        return ResponseEntity.ok(productDtoConverter.productToDto(productService.saveProduct(productRequest)));
    }

    @GetMapping("/find")
    public ResponseEntity<ProductDto> findProductById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to find Product by Id");

        return ResponseEntity.ok(productDtoConverter.productToDto(productService.findProductById(id)));
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ProductDto>> findAllProduct(){
        log.debug("REST Request to find all Products");

        return ResponseEntity.ok(productDtoConverter.productToDto(productService.findAllProduct()));
    }

    @GetMapping("/find/all/category/{id}")
    public ResponseEntity<List<ProductDto>> findAllProductByCatagory(@NotNull @RequestParam(name = "id") UUID categoryId){
        log.debug("REST Request to find all Products by Category");

        return ResponseEntity.ok(productDtoConverter.productToDto(productService.findAllProductByCategory(categoryId)));
    }

    @DeleteMapping("/delete")
    public void deleteProductById(@NotNull @RequestParam UUID id){
        log.debug("REST Request to delete Product by Id");

        productService.deleteProductById(id);
    }
}
