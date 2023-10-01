package com.example.springsecurityfilters.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MyController {

    @GetMapping("/free")
    public ResponseEntity<String> freePath(){
        return ResponseEntity.ok("This is a free path");
    }
    @GetMapping("/auth")
    public ResponseEntity<String> authPath(){
        return ResponseEntity.ok("This is an auth path");
    }

    @GetMapping("/api-content")
    public ResponseEntity<String> getContent(Principal principal){
        return ResponseEntity.ok("This is the content of api request by " +  principal.getName());
    }

}
