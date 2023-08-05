package com.example.carlosjr.functionalinterface.interfaces;


import org.junit.jupiter.api.Test;

class AnonymousFunctionalTest {

    @Test
    void doSomething() {

        AnonymousFunctional myGreetings = () ->{
            System.out.println("Greetings!");
        };

        myGreetings.doSomething();

    }
}