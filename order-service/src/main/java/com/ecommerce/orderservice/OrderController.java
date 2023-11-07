package com.ecommerce.orderservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all-orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

//    @CircuitBreaker(name = "product-service",fallbackMethod = "productServiceFallbackMethod")
    @PostMapping("/create-order/product-id/{productId}/quantity/{quantity}")
    public Order createOrder(@PathVariable("productId") Integer productId,@PathVariable("quantity") Integer  quantity){
        return orderService.createOrder(productId,quantity);
    }



    public Order productServiceFallbackMethod(Integer productId,Integer quantity,Exception ex){
        return new Order(-1,productId,0.0,"FALLBACK-ORDER");
    }
}
