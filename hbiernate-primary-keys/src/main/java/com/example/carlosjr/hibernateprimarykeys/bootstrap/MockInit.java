package com.example.carlosjr.hibernateprimarykeys.bootstrap;

import com.example.carlosjr.hibernateprimarykeys.models.Person;
import com.example.carlosjr.hibernateprimarykeys.repositories.PersonRepository;
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

        personRepository.deleteAll();

        Person p1 = Person.builder().name("John Green").build();
        Person p2 = Person.builder().name("Arnold Black").build();
        Person p3 = Person.builder().name("Thomas Blue").build();

        personRepository.saveAll(Arrays.asList(p1,p2,p3));

    }
}
