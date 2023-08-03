package com.example.carlosjr.ooconcepts.factory;

import external.service.email.EmailServiceFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FactoryPattern implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        EmailServiceFactory factory = new EmailServiceFactory("BRA");
        //System.out.println("\n \n");
        //factory.getEmailService().sendEmail("This message!");


    }
}
