package com.example.carlosjr.springwebfluxr2dbc.controller;

import com.example.carlosjr.springwebfluxr2dbc.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@SpringBootTest
@AutoConfigureWebTestClient
class PersonControllerTest {

    @Autowired
    WebTestClient webTestClient;

    Person person;

    final String BASE_URL = "/api/v1/person";

    @BeforeEach
    void setUp() {
        person = Person.builder()
                .name("John Green")
                .age(25)
                .isTall(true)
                .build();
    }

    @Test
    void shouldGetAPersonById() {

        webTestClient.get()
                .uri(BASE_URL, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody(Person.class);

    }

    @Test
    void shouldRetrievePeople() {

        webTestClient.get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);

    }

    @Test
    void shouldCreatePerson() {
        webTestClient.post().uri(BASE_URL)
                .body(Mono.just(getTestPerson()), Person.class)
                .header("Content-type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("/api/v1/person/4/id");
    }

    private Person getTestPerson(){
        return Person.builder()
                .name("Josh Purple")
                .age(25)
                .isTall(true)
                .build();
    }
}