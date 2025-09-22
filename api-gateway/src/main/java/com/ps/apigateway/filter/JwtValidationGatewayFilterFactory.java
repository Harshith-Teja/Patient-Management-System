package com.ps.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {


    private final WebClient webClient;

    //authServiceUrl is used because it changes based on the environment we are using (localhost, aws, etc...)
    public JwtValidationGatewayFilterFactory(WebClient.Builder webclientBuilder, @Value("${auth.service.url}") String authServiceUrl) {
        this.webClient = webclientBuilder.baseUrl(authServiceUrl).build();
    }

    //this class and method are used to intercept incoming http requests and control which ones to pass/reject
    @Override
    public GatewayFilter apply(Object config) {

        //exchange is a java obj, that contains properties related to request
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");

            if(token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange));
        };
    }
}
