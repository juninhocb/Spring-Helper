package com.carlosjr.test.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!local-discovery")
public class LocalhostRoutesConfig {
    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("dev-service",
                        r-> r.path("/api/v1*","/api/v1/*", "/api/v1/dev/*")
                        .uri("http://localhost:8081"))
                .route("dev2-service",
                        r-> r.path("/api/v1/*/dev2", "/api/v1/rparam/**")
                        .uri("http://localhost:8082"))
                .build();
    }

}
