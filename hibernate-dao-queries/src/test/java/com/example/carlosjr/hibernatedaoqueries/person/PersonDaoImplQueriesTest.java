package com.example.carlosjr.hibernatedaoqueries.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonDaoImplQueriesTest {

    @Autowired
    @Qualifier("personDaoImplQueries")
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
    void createPerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void findAlive() {
        List<Person> alivePeople = personDao.findAlive();
        assertThat(alivePeople.size()).isGreaterThan(1);
        System.out.println(alivePeople.size());
    }
}