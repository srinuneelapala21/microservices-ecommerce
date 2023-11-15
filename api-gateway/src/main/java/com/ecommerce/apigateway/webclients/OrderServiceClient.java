package com.ecommerce.apigateway.webclients;

import com.ecommerce.apigateway.dto.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {

    private String hostname = "http://ORDER-SERVICE/order-service";

    private final WebClient.Builder webClientBuilder;

    public Mono<OrderDetails> getOrder(int orderId){
        return webClientBuilder.build().get()
                .uri(hostname+"/get-order?orderId={orderId}",orderId)
                .retrieve()
                .bodyToMono(OrderDetails.class);
    }

}
