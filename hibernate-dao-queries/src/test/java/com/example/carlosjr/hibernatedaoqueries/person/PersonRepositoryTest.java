package com.example.carlosjr.hibernatedaoqueries.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;
    @Test
    void findPersonByName() {

        Person person = personRepository.findPersonByName("John Green").get();
        assertThat(person).isNotNull();
        System.out.println(person);

    }
}