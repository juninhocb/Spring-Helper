package com.example.carlosjr.distub.controllers;

import com.example.carlosjr.distub.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PropertyInjectedController {

    @Autowired
    @Qualifier("anotherGreetingServiceImpl")
    public GreetingService greetingService;

    public String getGreeting(){
        return  greetingService.sayGreetings();
    }

}
