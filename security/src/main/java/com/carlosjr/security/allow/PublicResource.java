package com.carlosjr.security.allow;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicResource {

    @GetMapping(value = "/public")
    public ResponseEntity<String> publicEndpoint(){
        return ResponseEntity.ok().body("You are allowed to access this endpoint!");
    }

}
