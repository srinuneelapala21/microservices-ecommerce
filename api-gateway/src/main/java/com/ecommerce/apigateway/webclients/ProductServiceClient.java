package com.ecommerce.apigateway.webclients;

import com.ecommerce.apigateway.dto.OrderDetails;
import com.ecommerce.apigateway.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductServiceClient {
    private String hostname = "http://PRODUCT-SERVICE/product-service";

    private final WebClient.Builder webClientBuilder;

    public Mono<Product> getOrder(int orderId){
        return webClientBuilder.build().get()
                .uri(hostname+"/get-product/{productId}",orderId)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
