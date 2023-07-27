package com.carlosjr.example.liquibase.bootstrap;

import com.carlosjr.example.liquibase.person.Person;
import com.carlosjr.example.liquibase.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class MockInit implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        if (personRepository.count() == 0 ){

            Person john = Person.builder().name("John Green").age(28).address("Blumenau-SC").build();
            Person richard = Person.builder().name("Richard Black").age(26).address("Ponta Grossa-PR").build();
            Person bob = Person.builder().name("Bob Blue").age(30).address("Joinville-SC").build();

            personRepository.saveAll(Arrays.asList(john, richard, bob));

        }


    }
}
