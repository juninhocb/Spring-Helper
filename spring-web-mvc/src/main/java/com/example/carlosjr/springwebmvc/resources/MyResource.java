package com.example.carlosjr.springwebmvc.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MyResource {

    @GetMapping("/stub")
    public ResponseEntity<String> restfullEndpoint(){
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }


}
