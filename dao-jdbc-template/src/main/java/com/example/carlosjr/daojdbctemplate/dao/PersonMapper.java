package com.example.carlosjr.daojdbctemplate.dao;

import com.example.carlosjr.daojdbctemplate.domain.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Person.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .city(rs.getString("city"))
                .build();

    }
}
