package com.carlosjr.test.dev2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Dev2Controller {

    @GetMapping("/{myName}/dev2")
    public ResponseEntity<String> sayMyName(@PathVariable(name = "myName") String name){
        return ResponseEntity.ok().body("Your name is: " + name);
    }

    @GetMapping("/rparam")
    public ResponseEntity<String> sayMyNameByParameter(@RequestParam(name= "name") String name){
        return ResponseEntity.ok().body("Your name is: " + name);
    }

}
