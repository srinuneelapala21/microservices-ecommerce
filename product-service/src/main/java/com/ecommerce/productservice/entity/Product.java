package com.ecommerce.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
}
