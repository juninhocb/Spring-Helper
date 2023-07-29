package com.example.carlosjr.distub.controllers;

import com.example.carlosjr.distub.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetterInjectedControllerTest {

    SetterInjectedController controller;

    @BeforeEach
    void setUp() {

        controller = new SetterInjectedController();
        controller.setGreetingService(new GreetingServiceImpl());

    }

    @Test
    void getGreetingService() {

        System.out.println(controller.greetingService.sayGreetings());

    }
}