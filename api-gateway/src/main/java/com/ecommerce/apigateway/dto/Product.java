package com.ecommerce.apigateway.dto;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
}