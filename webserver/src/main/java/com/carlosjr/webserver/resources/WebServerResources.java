package com.carlosjr.webserver.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping(value = "get-page")
public class WebServerResources {
    @GetMapping("/{name}")
    public ResponseEntity<String> getPage(@PathVariable String name) throws IOException {
        File file = new File(String.format("%s/templates/%s", System.getProperty("user.dir"), name));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        String content = new String(bis.readAllBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok().body(content);
    }

}
