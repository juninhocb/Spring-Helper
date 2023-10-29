package com.example.springhateoas.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {

    @Autowired
    TestRestTemplate restTemplate;
    String path = "/api/v1/person";

    Person person = Person.builder()
            .name("John Green")
            .age(28).build();

    @Test
    void shouldCreateAndGet(){
        ResponseEntity<Void> postResponse =
                restTemplate.postForEntity(path, person, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ResponseEntity<Person> getResponse =
                restTemplate.getForEntity(postResponse.getHeaders().getLocation(), Person.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(getResponse.getBody());

    }

}