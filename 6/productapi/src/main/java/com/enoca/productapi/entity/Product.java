package com.enoca.productapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_name",nullable = false)
    private String productName;

    @Column(name = "stock",nullable = false)
    private int stock;

    @Column(name = "price",nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;


}
