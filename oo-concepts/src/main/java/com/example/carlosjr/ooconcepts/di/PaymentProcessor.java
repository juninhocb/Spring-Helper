package com.example.carlosjr.ooconcepts.di;

import org.springframework.stereotype.Service;

@Service
public class PaymentProcessor {

    public void process(Double amount) throws InterruptedException {
        System.out.println("Processando pagamento...." + amount);
        Thread.sleep(1000);
        System.out.println("Pagamento processado");
    }

}
