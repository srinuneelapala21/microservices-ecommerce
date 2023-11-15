package com.ecommerce.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class RedirectFilter extends AbstractGatewayFilterFactory<RedirectFilter.Config> {

    public RedirectFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(RedirectFilter.Config config) {
        return (exchange, chain) -> {
            String headerVal = exchange.getRequest().getHeaders().getFirst(config.headerName);
            if(headerVal!=null) {
                String redirectPath = getRedirectPath(headerVal);
                URI newUri = UriComponentsBuilder.fromUri(exchange.getRequest().getURI())
                        .replacePath(redirectPath)
                        .build().toUri();
                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();
                return chain.filter(exchange.mutate().request(request).build());
            }
            return chain.filter(exchange);
        };
    }

    private  String getRedirectPath(String headerVal) {

        switch(headerVal.toLowerCase()){
            case "normaluser":
                return "/product-service/get-products/normalUser";
            case "goldenuser":
                return "/product-service/get-products/goldenUser";
            default:
                return "/product-service/others";
        }

    }

    public static class Config {
        private String headerName;

        public Config(String headerName) {
            this.headerName=headerName;
        }

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }
    }



}
