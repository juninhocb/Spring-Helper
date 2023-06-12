package com.carlojr.simpleoauth2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value = "/greetings")
public class ServerPublicResource {
    @GetMapping("/bypath/{name}")
    public ResponseEntity<String> greetingByPath(@PathVariable String name) {
        return ResponseEntity.ok().body("Hello " + name);
    }
    @GetMapping("/byquery")
    public ResponseEntity<String> greetingByQuery(@RequestParam(value="name", defaultValue = "unknown", required = false) String name){
        return ResponseEntity.ok().body("Hello, " + name + "✌️");
    }

}