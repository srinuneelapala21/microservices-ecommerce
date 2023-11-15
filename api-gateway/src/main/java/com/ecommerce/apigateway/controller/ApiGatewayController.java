package com.ecommerce.apigateway.controller;

import com.ecommerce.apigateway.dto.OrderDetails;
import com.ecommerce.apigateway.dto.Product;
import com.ecommerce.apigateway.webclients.OrderServiceClient;
import com.ecommerce.apigateway.webclients.ProductServiceClient;
import com.ecommerce.apigateway.webclients.TestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ApiGatewayController {

    public final OrderServiceClient orderServiceClient;

    public final ProductServiceClient productServiceClient;

    public final TestClient testClient;

    @GetMapping("/api/order/{orderId}")
    public Mono<OrderDetails> getOrderDetailsWithProduct(@PathVariable int orderId){
        return orderServiceClient.getOrder(orderId)
                .flatMap(order -> productServiceClient.getOrder(order.getProductId())
                        .map(product -> {
                            order.setProduct(product);
                            return order;
                        }));
    }


    @GetMapping("/api/test/{orderId}")
    public Mono<OrderDetails> getOrders(@PathVariable int orderId,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        return testClient.getOrder(orderId,authorizationHeader);
    }
}
