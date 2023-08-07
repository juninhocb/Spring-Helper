package com.example.carlosjr.mapstruct.mappers;

import com.example.carlosjr.mapstruct.model.Person;
import com.example.carlosjr.mapstruct.model.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    @Mapping(target = "createdTime", ignore = true)
    @Mapping(source = "stillAlive", target = "isAlive")
    Person personDtoToPerson(PersonDto dto);
    @Mapping(source = "isAlive", target = "stillAlive")
    PersonDto personToPersonDto(Person entity);


}
