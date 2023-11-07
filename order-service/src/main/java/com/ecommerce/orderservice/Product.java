package com.ecommerce.orderservice;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
}