package com.ecommerce.orderservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product-service/get-product/{productId}")
    Product getProductById(@PathVariable(name = "productId") Integer id);

    @PutMapping("/product-service/update-stock/product-id/{productId}/quantity/{quantity}")
    Product updateStock(@PathVariable(name = "productId") Integer productId,@PathVariable(name="quantity") Integer quantity);
}
