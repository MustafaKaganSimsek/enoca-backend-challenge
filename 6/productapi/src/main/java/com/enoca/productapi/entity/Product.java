package com.enoca.productapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "product")
public class Product extends Auditable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_name",nullable = false)
    private String name;

    @Column(name = "stock",nullable = false)
    private Integer stock;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;


}
