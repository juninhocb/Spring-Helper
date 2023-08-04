package com.example.carlosjr.daojdbctemplate.dao;

import com.example.carlosjr.daojdbctemplate.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonDaoImpl implements PersonDao {

    private final PersonMapper personMapper;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Person findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person where id = ? ", personMapper, id);
    }

    @Override
    public Person findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM person where name = ? ", personMapper, name);
    }

    @Override
    public Person findByNameAndCity(String name, String city) {
        return jdbcTemplate.queryForObject("SELECT * FROM person where name = ? AND city = ? ", personMapper, name, city);
    }

    @Override
    public Person saveNewPerson(Person person) {

        jdbcTemplate.update("INSERT INTO person (city, name) VALUES (?,?)", person.getCity(), person.getName());

        Long createdId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);

        return this.findById(createdId);
    }

    @Override
    public Person updatePerson(Person person) {

        jdbcTemplate.update("UPDATE person SET city = ?, name = ? WHERE id = ? ", person.getCity(),
                person.getName(), person.getId());

        return this.findById(person.getId());

    }

    @Override
    public void deletePerson(Long id) {

        jdbcTemplate.update("DELETE FROM person WHERE id = ? ", id);

    }
}
