package com.example.carlosjr.daojdbctemplate.dao;

import com.example.carlosjr.daojdbctemplate.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDaoImplTest {

    @Autowired
    PersonDao personDao;
    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {

        Person person = personDao.findById(1L);

        assertThat(person).isNotNull();

        System.out.println(person);

    }

    @Test
    void findByName() {
        Person person = personDao.findByName("John Green");

        assertThat(person).isNotNull();

        System.out.println(person);
    }

    @Test
    void findByNameAndCity() {
        Person person = personDao.findByNameAndCity("John Green", "Blumenau-SC");

        assertThat(person).isNotNull();

        System.out.println(person);
    }

    @Test
    void saveNewPerson() {

        Person personToCreate = Person.builder()
                .city("Florianopolis-SC")
                .name("Frank Purple")
                .build();

        Person createdPerson = personDao.saveNewPerson(personToCreate);

        assertThat(createdPerson).isNotNull();

        System.out.println(createdPerson);
    }

    @Test
    void updatePerson() {

        Person personToCreate = Person.builder()
                .city("Florianopolis-SC")
                .name("Frank Purple")
                .build();

        Person createdPerson = personDao.saveNewPerson(personToCreate);

        createdPerson.setCity("Itajai-SC");

        Person updatedPerson = personDao.updatePerson(createdPerson);

        assertThat(updatedPerson).isNotNull();

        System.out.println(updatedPerson);

    }

    @Test
    void deletePerson() {

        Person personToCreate = Person.builder()
                .city("Rio do sul-SC")
                .name("Frank Purple")
                .build();

        Person createdPerson = personDao.saveNewPerson(personToCreate);

        assertThat(createdPerson).isNotNull();

        personDao.deletePerson(createdPerson.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> personDao.findById(createdPerson.getId()));


    }
}