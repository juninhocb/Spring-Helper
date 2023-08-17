package com.example.carlosjr.scheduletasksdb.domain.mapper;

import com.example.carlosjr.scheduletasksdb.domain.entities.Person;
import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {
    Person dtoToEntity(PersonDto personDto);
    PersonDto entityToDto (Person person);


}
