package com.example.carlosjr.ooconcepts.di;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InvoiceHandle implements CommandLineRunner {

    private final PaymentProcessor paymentProcessor;

    public InvoiceHandle(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    @Override
    public void run(String... args) throws Exception {

        paymentProcessor.process(4.00);


    }
}
