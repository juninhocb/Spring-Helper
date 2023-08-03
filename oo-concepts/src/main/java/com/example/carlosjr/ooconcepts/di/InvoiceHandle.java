package com.example.carlosjr.ooconcepts.di;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InvoiceHandle implements CommandLineRunner {

    private final PaymentProrcessor paymentProrcessor;

    public InvoiceHandle(PaymentProrcessor paymentProrcessor) {
        this.paymentProrcessor = paymentProrcessor;
    }

    @Override
    public void run(String... args) throws Exception {

        paymentProrcessor.process(4.00);


    }
}
