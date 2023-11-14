package com.enoca.productapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product extends Auditable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "stock",nullable = false)
    private int stock;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "category_id",nullable = false)
    @JsonIgnoreProperties({"products"})
    private Category category;


}
