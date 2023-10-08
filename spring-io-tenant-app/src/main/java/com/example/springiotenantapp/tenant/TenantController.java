package com.example.springiotenantapp.tenant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/tenant")
public class TenantController {
    @GetMapping
    public ResponseEntity<String> getTenantId(){
        return ResponseEntity.ok(TenantContext.getTenantId());
    }
}
