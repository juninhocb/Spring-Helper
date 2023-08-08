package com.example.carlosjr.springwebfluxr2dbc.controller;

import com.example.carlosjr.springwebfluxr2dbc.model.Person;
import com.example.carlosjr.springwebfluxr2dbc.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/{personId}/id")
    public ResponseEntity<Mono<Person>> getPersonById(@PathVariable(name= "personId") Long id){
        return ResponseEntity.ok().body(personRepository.findById(id));
    }

    @GetMapping("/{personName}/name")
    public ResponseEntity<Mono<Person>> getPersonByName(@PathVariable(name = "personName") String name){
        return ResponseEntity.ok().body(personRepository.findByName(name));
    }

    @GetMapping
    public ResponseEntity<Flux<Person>> getPeople(){
        Flux<Person> people = personRepository.findAll();
        return ResponseEntity.ok().body(people);
    }

    @PostMapping
    public Mono<ResponseEntity<Void>> createPerson(@RequestBody Person person,
                                             UriComponentsBuilder ucb){

        /*final URI[] resourcePath = new URI[1];
        Mono<Person> persistedPerson = personRepository.save(person);
        persistedPerson.subscribe(p ->{
            resourcePath[0] = ucb
                    .path("/api/v1/person/{id}/id")
                    .buildAndExpand(p.getId())
                    .toUri();
        });


        return ResponseEntity.created(resourcePath[0]).build();*/


        return personRepository
                .save(person)
                .map(persistedPerson -> {
                    return ResponseEntity
                            .created(ucb
                                    .path("/api/v1/person/{id}/id")
                                    .buildAndExpand(persistedPerson.getId())
                                    .toUri()).build();
        });

    }

    @DeleteMapping("/{personId}")
    public Mono<ResponseEntity<Void>> deletePerson(@PathVariable Long personId){
        personRepository.deleteById(personId).subscribe();
        return Mono.just(ResponseEntity.noContent().build());
    }


}
