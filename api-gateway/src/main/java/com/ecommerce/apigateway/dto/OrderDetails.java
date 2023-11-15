package com.ecommerce.apigateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value={"productId"},allowSetters = true)
public class OrderDetails {
    private Integer orderId;
    @JsonProperty("productId")
    private Integer productId;
    private Product product;
    private Double totalAmount;
    private String status;
}
