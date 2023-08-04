package com.example.carlosjr.daojdbctemplate.dao;

import com.example.carlosjr.daojdbctemplate.domain.Person;

public interface PersonDao {
    Person findById(Long id);
    Person findByName(String name);

    Person findByNameAndCity(String name, String city);
    Person saveNewPerson(Person person);

    Person updatePerson(Person person);
    void deletePerson(Long id);

}
