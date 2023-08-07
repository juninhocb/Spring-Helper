package com.example.carlosjr.mapstruct.mappers;

import com.example.carlosjr.mapstruct.model.Address;
import com.example.carlosjr.mapstruct.model.Person;
import com.example.carlosjr.mapstruct.model.PersonDto;
import com.example.carlosjr.mapstruct.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    AddressRepository addressRepository;

    Address address;

    @BeforeEach
    void setUp() {
        address = addressRepository
                .findById("Blumenau").get();
    }

    @Test
    void shouldMapPersonToDto() {

        System.out.println(address.getCity());

        Person person = Person.builder()
                .id(3L)
                .name("John Green")
                .age(3)
                .isAlive(true)
                .address(address)
                .document(UUID.randomUUID()).build();

        PersonDto personDto = personMapper.personToPersonDto(person);

        assertThat(personDto.getName()).isEqualTo("John Green");

        System.out.println(personDto);

        assertThat(personDto.getAddress().getProvince()).isNotNull();

        System.out.println(personDto.getAddress().getProvince());
    }

    @Test
    void shouldMapDtoToEntity() {

        PersonDto personDto = PersonDto.builder()
                .name("John Green")
                .age(3)
                .stillAlive(true)
                .address(address)
                .document(UUID.randomUUID()).build();

        Person person = personMapper.personDtoToPerson(personDto);

        assertThat(person.getName()).isEqualTo("John Green");

        System.out.println(person);

        assertThat(person.getAddress().getCity()).isNotNull();

        System.out.println(person.getAddress().getCity());
    }
}