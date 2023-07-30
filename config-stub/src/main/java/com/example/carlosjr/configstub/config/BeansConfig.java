package com.example.carlosjr.configstub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:custom.properties")
@Configuration
public class BeansConfig {

    @Bean
    public SetterFakeDatasourceConfig getFakeDatasource(){
        return new SetterFakeDatasourceConfig();
    }

    @Bean
    public String getCustomFooProperty(@Value("${com.carlosjr.custom.foo}")String foo){
        return foo;
    }

    @Bean
    public String getErrorWhenNotQualifier(){
        return "Error";
    }

}
