package com.example.springsoapclient;

import com.example.springsoapclient.ws.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringSoapClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSoapClientApplication.class, args);
    }

}

@RestController
@RequestMapping("/person")
class PersonController{

    private final PersonClientService personClientService;

    public PersonController(PersonClientService personClientService){
        this.personClientService = personClientService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Person> getPersonByName(@PathVariable String name){
        return new ResponseEntity<>(personClientService.getPerson(name).getPerson(), HttpStatus.OK);
    }

}
