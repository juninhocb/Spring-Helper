package com.carlosjr.email.email;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value="/email")
public class EmailResource {
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send")
    public ResponseEntity<Void> sendEmailToClient(@RequestBody @Valid EmailDTO emailDTO, UriComponentsBuilder ucb) throws URISyntaxException {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        Long generatedId = emailService.sendEmail(email);
        URI uriOfObject = ucb
                .path("email/find/{emailId}")
                .buildAndExpand(generatedId)
                .toUri();
        return ResponseEntity.created(uriOfObject).build();
    }
    @GetMapping(value = "/find/{emailId}")
    public ResponseEntity<Email> findById(@PathVariable Long emailId){
        Email email = emailService.findById(emailId);
        return ResponseEntity.ok().body(email);
    }


}

