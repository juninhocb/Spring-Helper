package com.example.carlosjr.distub.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayGreetings() {
        return "Hello World!";
    }
}
