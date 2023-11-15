package com.enoca.productapi.dto;

import java.util.Date;
import java.util.UUID;



import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;

    private String name;

    private int stock;

    private double price;

    private String description;

    private CategoryDto category;

    private String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    private String lastModifiedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;
}
