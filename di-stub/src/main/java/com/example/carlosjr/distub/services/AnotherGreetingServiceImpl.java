package com.example.carlosjr.distub.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class AnotherGreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreetings() {
        return "Hello World of another!";
    }
}
