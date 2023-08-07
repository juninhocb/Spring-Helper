package com.example.carlosjr.hibernatedaoqueries.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDaoImplTest {

    @Autowired
    @Qualifier("personDaoImpl")
    PersonDao personDao;
    @Test
    void findPersonById() {
        Person person = personDao.findPersonById(3L);
        assertThat(person).isNotNull();
        System.out.println(person);
    }

    @Test
    void findPersonByName() {
        Person person = personDao.findPersonByName("John Green");
        assertThat(person).isNotNull();
        System.out.println(person);
    }

    @Test
    void findPeople() {
        List<Person> personList = personDao.findPeople();
        assertThat(personList.size()).isEqualTo(3);
        System.out.println(personList.get(1).getName()); //Peter Black
    }

    @Test
    @DirtiesContext
    void createPerson() {
        Person person = Person.builder()
                .name("Philip Orange")
                .isAlive(true)
                .age(27)
                .build();

        Long persistedId = personDao.createPerson(person);

        assertThat(persistedId).isNotNull();

    }
    @Test
    @DirtiesContext
    void deletePerson() {

        personDao.deletePerson(1L);
        Person person = personDao.findPersonById(1L);
        assertThat(person).isNull();

    }

    @Test
    void findAlive() {
        List<Person> alivePeople = personDao.findAlive();
        assertThat(alivePeople.size()).isGreaterThan(1);
        System.out.println(alivePeople.size());
    }
}