package com.carlosjr.test.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local-discovery")
public class LoadBalancedRoutes {

    @Bean
    public RouteLocator balancedRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("DEV-SERVICE",
                        r-> r.path("/api/v1*","/api/v1/*", "/api/v1/dev/*")
                        .filters(f -> f.circuitBreaker(c->
                                c.setName("devCB")
                                .setFallbackUri("forward:/failover")
                                .setRouteId("dev-failover")))
                        .uri("lb://127.0.0.1"))
                .route("failover",
                            r -> r.path("/failover/**")
                            .uri("lb://failover"))
                .build();
    }

}
