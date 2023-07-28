package com.carlosjr.example.secproject.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stub")
public class StubResources {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getResource(){
        return "Success";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String postResource(){
        return "Success for port";
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteResource(){return "Success for delete";}

}
