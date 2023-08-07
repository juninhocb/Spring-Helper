package com.example.carlosjr.mapstruct.bootstrap;

import com.example.carlosjr.mapstruct.model.Address;
import com.example.carlosjr.mapstruct.model.Person;
import com.example.carlosjr.mapstruct.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MockData implements CommandLineRunner {
    private final PersonRepository personRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Person> listOfPerson = getPeople();
        personRepository.saveAll(listOfPerson);
    }

    private List<Person> getPeople() {
        Address address = Address.builder()
                .city("Blumenau")
                .province("Santa Catarina")
                .build();

        Person person = Person.builder()
                .id(3L)
                .name("John Green")
                .age(3)
                .isAlive(true)
                .address(address)
                .document(UUID.randomUUID()).build();

        Person person2 = Person.builder()
                .id(4L)
                .name("Peter Black")
                .age(23)
                .isAlive(true)
                .address(address)
                .document(UUID.randomUUID()).build();

        Person person3 = Person.builder()
                .id(5L)
                .name("Josh Red")
                .age(22)
                .isAlive(true)
                .address(address)
                .document(UUID.randomUUID()).build();

        return Arrays.asList(person, person2, person3);
    }

}
