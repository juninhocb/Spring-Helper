package com.example.carlosjr.functionalinterface.interfaces;

import org.springframework.stereotype.Service;

@Service
public class EmailFunctionalTraditional implements EmailFunctional {
    @Override
    public void sendEmail(String message) {
        System.out.println("Sending email with message " + message);
    }
}
