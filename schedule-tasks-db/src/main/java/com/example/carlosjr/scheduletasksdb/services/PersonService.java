package com.example.carlosjr.scheduletasksdb.services;

import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PersonService {

    PersonDto findById(Long id);
    PersonDto findByName(String name);
    Set<PersonDto> findAll(Pageable pageable);
    Long create(PersonDto personDto);
    void update(Long id, PersonDto personDto);
    void updateSchedule(Long id, String time);
    void delete(Long id);

}
