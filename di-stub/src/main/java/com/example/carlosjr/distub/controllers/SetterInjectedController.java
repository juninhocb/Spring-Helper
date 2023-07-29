package com.example.carlosjr.distub.controllers;

import com.example.carlosjr.distub.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SetterInjectedController {

    public GreetingService greetingService;

    public GreetingService getGreetingService() {
        return greetingService;
    }
    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
