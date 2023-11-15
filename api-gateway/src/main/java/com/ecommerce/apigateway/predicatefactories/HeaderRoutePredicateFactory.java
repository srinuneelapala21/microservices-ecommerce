package com.ecommerce.apigateway.predicatefactories;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;



public class HeaderRoutePredicateFactory
        extends AbstractRoutePredicateFactory<HeaderRoutePredicateFactory.Config> {

    public HeaderRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            // Match requests with a specific header value
            return exchange.getRequest().getHeaders().containsKey(config.headerName) &&
                    exchange.getRequest().getHeaders().getFirst(config.headerName).equals(config.headerValue);
        };
    }

    public static class Config {
        private String headerName;
        private String headerValue;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public String getHeaderValue() {
            return headerValue;
        }

        public void setHeaderValue(String headerValue) {
            this.headerValue = headerValue;
        }
    }
}
