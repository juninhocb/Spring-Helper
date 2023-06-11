package com.carlos.serversecurity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/greetings")
public class ServerTestResource {

    @GetMapping("/bypath/{name}")
    public ResponseEntity<String> greetingByPath(@PathVariable String name){
        System.out.printf("The name is %s", name +"\n");
        return ResponseEntity.ok().body("Hello, " + name + "✌️");
    }

    @GetMapping("/byquery")
    public ResponseEntity<String> greetingByQuery(@RequestParam(value="name", defaultValue = "unknown", required = false) String name){
        return ResponseEntity.ok().body("Hello, " + name + "✌️");
    }

}
