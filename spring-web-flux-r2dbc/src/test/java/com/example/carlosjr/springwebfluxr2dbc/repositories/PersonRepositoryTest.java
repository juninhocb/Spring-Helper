package com.example.carlosjr.springwebfluxr2dbc.repositories;

import com.example.carlosjr.springwebfluxr2dbc.bootstrap.InitData;
import com.example.carlosjr.springwebfluxr2dbc.config.DatabaseConfig;
import com.example.carlosjr.springwebfluxr2dbc.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;


@DataR2dbcTest
@Import({DatabaseConfig.class, InitData.class})
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void shouldSaveNewPerson() {
        personRepository.save(getTestPerson())
                .subscribe(person -> {
                    System.out.println(person);
                });

    }

    @Test
    void shouldDeletePerson() {
        Person person =  personRepository.findByName("John Green").block();
        long initialRepositoryCount = personRepository.count().block();
        System.out.println(initialRepositoryCount);
        personRepository.deleteById(person.getId()).subscribe();
        long finalRepositoryCount = personRepository.count().block();
        System.out.println(finalRepositoryCount);
        assertThat(initialRepositoryCount).isGreaterThan(finalRepositoryCount);

    }

    private Person getTestPerson(){
        return Person.builder()
                .name("John Green")
                .age(15)
                .isTall(true)
                .build();
    }

}