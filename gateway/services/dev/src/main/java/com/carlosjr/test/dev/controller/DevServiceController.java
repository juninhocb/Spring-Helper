package com.carlosjr.test.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DevServiceController {

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok().body("This is the home dev service");
    }
    @GetMapping("/greetings")
    public ResponseEntity<String> greetings(){
        return ResponseEntity.ok().body("Greetings from dev service");
    }
    @GetMapping("/dev/bla")
    public ResponseEntity<String> bla(){
        return ResponseEntity.ok().body("Bla");
    }

}
