package com.carlosjr.security.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CustomerMock implements CommandLineRunner {
    @Autowired
    CustomerRepository _customerRepository;

    @Override
    public void run(String... args) throws Exception {
        Customer c1 = new Customer("james", "password1");
        Customer c2 = new Customer("peter", "password2");
        _customerRepository.saveAll(Arrays.asList(c1,c2));
    }
}
