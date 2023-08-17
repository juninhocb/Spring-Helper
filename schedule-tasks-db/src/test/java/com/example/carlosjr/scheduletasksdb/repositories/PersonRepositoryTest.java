package com.example.carlosjr.scheduletasksdb.repositories;

import com.example.carlosjr.scheduletasksdb.domain.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;
    @Test
    void shouldRetrieveEmployeeById() {
        Optional<Person> person =  personRepository.findById(1L);
        System.out.println(person.get().getName());
    }
}