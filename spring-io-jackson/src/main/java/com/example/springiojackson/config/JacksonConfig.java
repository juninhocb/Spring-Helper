package com.example.springiojackson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
public class JacksonConfig {
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
*/

@Configuration
public class JacksonConfig{
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> {
            builder
                    .failOnEmptyBeans(false)
                    .failOnUnknownProperties(true)
                    .build();
        };
    }

}

