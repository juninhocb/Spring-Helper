package com.carlosjr.security.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

    @Autowired
    CustomerRepository _customerRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id){
        Optional<Customer> customer = _customerRepository.findById(id);
        return ResponseEntity.ok().body(customer.orElseThrow());
    }

}
