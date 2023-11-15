package com.ecommerce.apigateway.config;

import com.ecommerce.apigateway.filter.AuthFilter;
import com.ecommerce.apigateway.predicatefactories.HeaderRoutePredicateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpringConfig {
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }


    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    @Bean
    public HeaderRoutePredicateFactory customHeaderPredicate(){
        return new HeaderRoutePredicateFactory();
    }

}
