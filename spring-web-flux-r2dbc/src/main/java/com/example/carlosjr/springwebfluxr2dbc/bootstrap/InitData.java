package com.example.carlosjr.springwebfluxr2dbc.bootstrap;

import com.example.carlosjr.springwebfluxr2dbc.model.Person;
import com.example.carlosjr.springwebfluxr2dbc.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        personRepository.saveAll(getPeopleToStore()).subscribe(
                person -> {
                    System.out.println(person);
                }
        );

    }

    private List<Person> getPeopleToStore(){
        Person person1  = Person.builder()
                .name("John Green")
                .age(15)
                .isTall(true)
                .build();

        Person person2  = Person.builder()
                .name("Peter Blue")
                .age(22)
                .isTall(false)
                .build();

        Person person3  = Person.builder()
                .name("Gary Black")
                .age(25)
                .isTall(true)
                .build();

        return Arrays.asList(person1,person2,person3);
    }
}
