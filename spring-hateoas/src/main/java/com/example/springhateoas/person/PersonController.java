package com.example.springhateoas.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository repository;
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable UUID id){
        return new ResponseEntity<>(repository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople(){
        List<Person> people = repository.findAll();
        people.forEach(p -> {
            p.add(linkTo(methodOn(PersonController.class).getPerson(p.getId())).withSelfRel());
        });
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> createPerson(@RequestBody Person person,
                                             UriComponentsBuilder ucb){
        URI resPath = ucb
                .path("/api/v1/person/{personId}")
                .buildAndExpand(repository.save(person).getId())
                .toUri();
        return ResponseEntity.created(resPath).build();
    }
}
