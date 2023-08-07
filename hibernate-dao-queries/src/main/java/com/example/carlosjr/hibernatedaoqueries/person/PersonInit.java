package com.example.carlosjr.hibernatedaoqueries.person;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class PersonInit implements CommandLineRunner {
    private final PersonRepository personRepository;
    @Override
    public void run(String... args) throws Exception {
        Person p1 = Person.builder()
                .name("John Green")
                .isAlive(true)
                .age(33)
                .build();

        Person p2 = Person.builder()
                .name("Peter Black")
                .isAlive(true)
                .age(37)
                .build();

        Person p3 = Person.builder()
                .name("Anna Gray")
                .isAlive(false)
                .age(42)
                .build();

        personRepository.saveAll(Arrays.asList(p1,p2,p3));

    }
}
