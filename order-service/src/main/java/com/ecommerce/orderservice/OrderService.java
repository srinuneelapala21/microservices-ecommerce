package com.ecommerce.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public ProductClient productClient;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrder(int orderId){
        return orderRepository.findById(orderId).orElse(null);
    }
    public Order createOrder(Integer productId,Integer quantity) {

        //call to the product serivce
        Product product = productClient.getProductById(productId);
        if (product.getStock() != 0  && product.getStock() >= quantity){
            Order order = new Order();
            order.setStatus("ORDER-PLACED");
            order.setProductId(productId);
            order.setTotalAmount(quantity * product.getPrice());
            product.setStock(product.getStock() - quantity);
            productClient.updateStock(productId,quantity);
            return orderRepository.save(order);
        }
        return null;
    }
}
