package com.example.carlosjr.springwebfluxr2dbc.controller;

import com.example.carlosjr.springwebfluxr2dbc.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@WebFluxTest(PersonController.class)
@SpringBootTest
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
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .value(person -> {
                    System.out.println(person.getName());
                });

    }

    @Test
    void shouldRetrievePeople() {

        webTestClient.get()
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .value(person -> {
                    System.out.println(person);
                    assertThat(person).isNotNull();
                });

    }

    private Person getTestPerson(){
        return Person.builder()
                .name("Josh Purple")
                .age(25)
                .isTall(true)
                .build();
    }
}