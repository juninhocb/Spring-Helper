package com.example.carlosjr.hibernateprimarykeys.repositories;

import com.example.carlosjr.hibernateprimarykeys.models.NameId;
import com.example.carlosjr.hibernateprimarykeys.models.PersonComposite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonCompositeRepositoryTest {

    @Autowired
    PersonCompositeRepository personCompositeRepository;
    @Test
    void shouldGetPersonCompositeById(){

        Optional<PersonComposite> personFromDb = personCompositeRepository
                .findById(NameId.builder().firstName("John").lastName("Green").build());

        assertThat(personFromDb.get()).isNotNull();

        System.out.println("Person print: " + personFromDb.get());

    }

}