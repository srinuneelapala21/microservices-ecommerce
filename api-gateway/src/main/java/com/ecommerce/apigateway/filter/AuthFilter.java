package com.ecommerce.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;


    private final WebClient.Builder webClientBuilder;

    //constructor injection
    public AuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(AuthFilter.Config config) {

        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
//                try {
                    //REST call to AUTH service
                   // Boolean isValidToken = authClient.validateToken(authHeader);
               return webClientBuilder.build().get().uri("http://AUTH-SERVICE/auth-service/validate-token?token=" + authHeader)
                        .retrieve().bodyToMono(Token.class)
                        .flatMap(token -> {
                            System.out.println("Response Content: " + token);
                                    if (token.isValid) {
                                        System.out.println("Valid Token");
                                        return chain.filter(exchange); // Continue processing the request
                                    } else {
                                        System.out.println("Invalid access...!");
                                        throw new RuntimeException("Unauthorized access to the application");
                                    }
                                }
                        );


            }
            return chain.filter(exchange);
        });
    }


    public static class Config{

    }
}
