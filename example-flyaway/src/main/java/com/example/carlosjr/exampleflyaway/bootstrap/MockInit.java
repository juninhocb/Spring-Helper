package com.example.carlosjr.exampleflyaway.bootstrap;

import com.example.carlosjr.exampleflyaway.person.Person;
import com.example.carlosjr.exampleflyaway.person.PersonRepository;
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

        if (personRepository.count() == 0){
            Person p1 = Person.builder().name("James Black").age(29).isAlive(true).address("Blumenau-SC").build();
            Person p2 = Person.builder().name("Charles Green").age(31).isAlive(false).address("Joinvile-SC").build();
            Person p3 = Person.builder().name("Dany Blue").age(30).isAlive(true).address("Florianopolis-SC").build();
            personRepository.saveAll(Arrays.asList(p1,p2,p3));
        }

    }
}
