package com.enoca.productapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnTransformer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category extends Auditable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name",unique = true)
    @ColumnTransformer(write = "Lower(?)")
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties({"category"})
    private Set<Product> products;


}
