package com.example.carlosjr.springfluxfunctionalendpoints.person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonRepository {
    Mono<Person> createPerson(Mono<Person> personMono);
    Mono<Person> findById(Integer id);
    Mono<Person> findByName(String name);
    Flux<Person> findAll();
    void deletePerson(Integer id);

}
