package com.example.carlosjr.functionalinterface.interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailFunctionalTest {

    EmailFunctional emailFunctional;

    @Test
    void emailFunctionalImplTest() {
        emailFunctional = new EmailFunctionalTraditional();
        emailFunctional.sendEmail("Greetings from traditional interface");

    }

    @Test
    void directlyImplemented() {

        emailFunctional = message ->
                System.out.println("Directly sending " + message);

        emailFunctional.sendEmail("Greetings from functional interface");

        EmailFunctional anotherDynamicImpl = message -> {
            System.out.println("The first print!");
            System.out.println("The second with message! " + message);
        };

        anotherDynamicImpl.sendEmail("Lets show this message!");

    }
}