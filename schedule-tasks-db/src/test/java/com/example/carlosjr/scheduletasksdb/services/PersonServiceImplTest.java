package com.example.carlosjr.scheduletasksdb.services;

import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceImplTest {

    @Autowired
    PersonService personService;

    @Test
    void handleUpdate() {

        personService.update(1L, getPersonDto());

        assertThat(personService.findById(1L).getName()).isEqualTo("Julius Orange");

    }

    @Test
    void shouldReturnPersonById() {
        PersonDto personDto=  personService.findById(1L);
        System.out.println(personDto.getName());
    }

    private PersonDto getPersonDto(){
        return PersonDto.builder()
                .age(25)
                .enabled(true)
                .email("my-email@example.com")
                .name("Julius Orange")
                .build();
    }

}