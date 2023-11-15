package com.ecommerce.apigateway.webclients;

import com.ecommerce.apigateway.dto.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TestClient {
    private String hostname = "http://API-GATEWAY/order-service";

    private final WebClient.Builder webClientBuilder;

    public Mono<OrderDetails> getOrder(int orderId,String autorizationHeader){
        return webClientBuilder.build().get()
                .uri(hostname+"/get-order?orderId={orderId}",orderId)
                .header(HttpHeaders.AUTHORIZATION,autorizationHeader)
                .retrieve()
                .bodyToMono(OrderDetails.class);
    }
}
