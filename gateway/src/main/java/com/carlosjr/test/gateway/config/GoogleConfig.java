package com.carlosjr.test.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("google")
@Configuration
public class GoogleConfig {
    static {
        System.out.println("Hello, is there anybody in there?");
    }
    @Bean
    public RouteLocator googleConfigRout(RouteLocatorBuilder builder){
         return builder.routes()
                .route("google", r -> r.path("/search")
                .uri("https://google.com"))
                .build();
    }

}
