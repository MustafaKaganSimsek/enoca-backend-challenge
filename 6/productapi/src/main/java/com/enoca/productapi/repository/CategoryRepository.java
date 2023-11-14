package com.enoca.productapi.repository;

import com.enoca.productapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsByName(String name);
}
