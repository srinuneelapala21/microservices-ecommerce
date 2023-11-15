package com.ecommerce.apigateway.config;

import com.ecommerce.apigateway.filter.AuthFilter;
import com.ecommerce.apigateway.filter.RedirectFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthFilter authFilter,RedirectFilter redirectFilter) {
        return builder.routes()
            .route("order-service", r -> r
                    .path("/order-service/**")
                    .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                    .uri("lb://ORDER-SERVICE")

            )
            .route("product-service", r -> r
                    .path("/product-service/get-product/**")
                    .and()
                    .header("User-Type", "normalUser")
                    .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                    .uri("lb://PRODUCT-SERVICE")

            )
            .route("product-service2", r -> r
                    .path("/product-service/get-all-products")
                    .and()
                    .header("User-Type", "goldenUser")
                    .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                    .uri("lb://PRODUCT-SERVICE")

            )
            .route("auth-service", r -> r
                .path("/auth-service/**")
                .uri("lb://AUTH-SERVICE")
            )
            .route("redirecting-route",r -> r
                    .path("/product-service/products")
                    .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config()))
//                                    .filter(redirectToGoldenUserIfHeader("User-Type","goldenUser"))
//                                    .filter(redirectToNormalUserIfHeader("User-Type", "normalUser"))
                                    .filter(redirectFilter.apply(new RedirectFilter.Config("User-Type")))
                            )
                    .uri("lb://PRODUCT-SERVICE")


            )
            .build();
    }
//    private GatewayFilter redirectToGoldenUserIfHeader(String headerName, String headerValue) {
//        return (exchange, chain) -> {
//            if (headerValue.equals(exchange.getRequest().getHeaders().getFirst(headerName))) {
//                URI newUri = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
//                        .replacePath("/product-service/get-products/goldenUser")
//                        .build().toUri();
//                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
//                return chain.filter(exchange.mutate().request(request).build());
//            }
//            return chain.filter(exchange);
//        };
//    }
//
//    private GatewayFilter redirectToNormalUserIfHeader(String headerName, String headerValue) {
//        return (exchange, chain) -> {
//            if (headerValue.equals(exchange.getRequest().getHeaders().getFirst(headerName))) {
//                URI newUri = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
//                        .replacePath("/product-service/get-products/normalUser")
//                        .build().toUri();
//                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
//                return chain.filter(exchange.mutate().request(request).build());
//            }
//            return chain.filter(exchange);
//        };
//    }

}
