package com.carlosjr.test.failoverdev.config;

import com.carlosjr.test.failoverdev.web.FailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;


import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction failoverRoute(FailHandler failHandler) {
        return route(GET("/failover").and(accept(MediaType.APPLICATION_JSON)), failHandler::listError);
    }

}
