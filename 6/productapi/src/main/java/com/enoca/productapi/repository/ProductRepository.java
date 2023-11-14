package com.enoca.productapi.repository;

import com.enoca.productapi.entity.Category;
import com.enoca.productapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategory(Category category);

    @Query("select case when count(c)> 0 then true else false end from Category c where c.id = :id")
    boolean existsCategoryForProduct(@Param("id") UUID id);

}
