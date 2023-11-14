package com.enoca.productapi.dto;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class CategoryRequest {

    @NotBlank(message = "Name is madotory")
    private String name;
}
