package com.example.webfluxdemo.infrastructure;

import com.example.webfluxdemo.domain.Person;
import com.example.webfluxdemo.infrastructure.PersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person michael = Person.builder()
            .id(1)
            .name("Michael Green")
            .city("Blumenau").build();

    Person sam = Person.builder()
            .id(2)
            .name("Sam Red")
            .city("São Paulo").build();

    Person josh = Person.builder()
            .id(3)
            .name("Josh Yellow")
            .city("Salvador").build();

    Person james = Person.builder()
            .id(4)
            .name("James Purple")
            .city("Fortaleza")
            .build();

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(michael);
    }

    @Override
    public Flux<Person> findAll() {

        return Flux.just(michael, sam, josh, james);
    }
}
