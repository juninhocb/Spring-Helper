package com.example.springsecurityfilters.api;

import com.example.springsecurityfilters.models.AuthToken;
import com.example.springsecurityfilters.models.Users;
import com.example.springsecurityfilters.token.TokenUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@RequestBody Users user){
        if (user.username().equals("me") && user.password().equals("myPassword")){

            return ResponseEntity.ok().body(TokenUtil.encodeToken(user));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

}
