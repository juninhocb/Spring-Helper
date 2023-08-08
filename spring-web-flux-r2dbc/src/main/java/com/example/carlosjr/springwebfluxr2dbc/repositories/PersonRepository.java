package com.example.carlosjr.springwebfluxr2dbc.repositories;

import com.example.carlosjr.springwebfluxr2dbc.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, Long> {

    Mono<Person> findByName(String name);

}
