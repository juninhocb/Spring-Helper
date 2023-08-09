package com.example.carlosjr.springfluxfunctionalendpoints.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PersonRouterConfig {

    public static final String BASE_URL = "/api/v1/person";
    @Bean
    public RouterFunction<ServerResponse> personRoutes(PersonController controller){
        return route()
                .GET(BASE_URL + "/{personId}", accept(APPLICATION_JSON), controller::getPersonById)
                .POST(BASE_URL, accept(APPLICATION_JSON), controller::createPerson)
                .build();

    }

}
