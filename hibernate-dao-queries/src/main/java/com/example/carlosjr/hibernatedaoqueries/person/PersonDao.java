package com.example.carlosjr.hibernatedaoqueries.person;

import java.util.List;

public interface PersonDao {
    Person findPersonById(Long id);
    Person findPersonByName(String name);
    List<Person> findPeople();
    Long createPerson(Person person);
    void deletePerson(Long id);
    List<Person> findAlive();

}
