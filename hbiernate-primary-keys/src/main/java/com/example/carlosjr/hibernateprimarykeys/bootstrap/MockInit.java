package com.example.carlosjr.hibernateprimarykeys.bootstrap;

import com.example.carlosjr.hibernateprimarykeys.models.*;
import com.example.carlosjr.hibernateprimarykeys.repositories.PersonCompositeRepository;
import com.example.carlosjr.hibernateprimarykeys.repositories.PersonRepository;
import com.example.carlosjr.hibernateprimarykeys.repositories.PersonStringRepository;
import com.example.carlosjr.hibernateprimarykeys.repositories.PersonUuidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MockInit implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final PersonUuidRepository personUuidRepository;
    private final PersonStringRepository personStringRepository;
    private final PersonCompositeRepository personCompositeRepository;

    @Override
    public void run(String... args) throws Exception {

        mockSimplePerson();
        mockUuidPerson();
        mockStringPerson();
        mockCompositePerson();

    }

    private void mockCompositePerson() {

        NameId nameId1 = NameId.builder().firstName("John").lastName("Green").build();
        NameId nameId2 = NameId.builder().firstName("Arnold").lastName("Black").build();
        NameId nameId3 = NameId.builder().firstName("Thomas").lastName("Blue").build();

        PersonComposite p1 = PersonComposite.builder().nameId(nameId1).name("Person comp1 ").build();
        PersonComposite p2 = PersonComposite.builder().nameId(nameId2).name("Person comp2 ").build();
        PersonComposite p3 = PersonComposite.builder().nameId(nameId3).name("Person comp3").build();

        personCompositeRepository.saveAll(Arrays.asList(p1,p2,p3));

    }

    private void mockStringPerson() {
        PersonString p1 = PersonString.builder().upc("CODEFORTHIS1").name("John Green").build();
        PersonString p2 = PersonString.builder().upc("CODEFORTHIS2").name("Arnold Black").build();
        PersonString p3 = PersonString.builder().upc("CODEFORTHIS3").name("Thomas Blue").build();
        personStringRepository.saveAll(Arrays.asList(p1,p2,p3));
    }

    private void mockUuidPerson() {

        PersonUuid p1 = PersonUuid.builder().name("John Green").build();
        PersonUuid p2 = PersonUuid.builder().name("Arnold Black").build();
        PersonUuid p3 = PersonUuid.builder().name("Thomas Blue").build();
        personUuidRepository.saveAll(Arrays.asList(p1,p2,p3));
    }

    private void mockSimplePerson(){
        personRepository.deleteAll();  //not need because we're using h2 database (create-drop)

        Person p1 = Person.builder().name("John Green").build();
        Person p2 = Person.builder().name("Arnold Black").build();
        Person p3 = Person.builder().name("Thomas Blue").build();

        personRepository.saveAll(Arrays.asList(p1,p2,p3));
    }

}
