package com.example.springsoapclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WsConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in pom.xml
        marshaller.setContextPath("com.example.springsoapclient.ws");
        return marshaller;
    }

    @Bean
    public PersonClientService personClient(Jaxb2Marshaller marshaller){
        PersonClientService personClientService = new PersonClientService();
        personClientService.setDefaultUri("http://localhost:8080/ws");
        personClientService.setMarshaller(marshaller);
        personClientService.setUnmarshaller(marshaller);
        return personClientService;
    }
}
