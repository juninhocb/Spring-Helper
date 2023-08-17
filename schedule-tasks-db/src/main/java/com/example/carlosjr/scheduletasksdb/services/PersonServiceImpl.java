package com.example.carlosjr.scheduletasksdb.services;

import com.example.carlosjr.scheduletasksdb.domain.entities.Person;
import com.example.carlosjr.scheduletasksdb.domain.mapper.PersonMapper;
import com.example.carlosjr.scheduletasksdb.repositories.PersonRepository;
import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import com.example.carlosjr.scheduletasksdb.rest.exceptions.ResourceNotFoundException;
import com.example.carlosjr.scheduletasksdb.rest.exceptions.TimeFormatNotSupportedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    @Override
    public PersonDto findById(Long id) {
        return personMapper.entityToDto(handleFind(id));
    }

    @Override
    public PersonDto findByName(String name) {
        return personMapper.entityToDto(handleFind(name));
    }

    @Override
    public Set<PersonDto> findAll(Pageable pageable) {
        return personRepository
                .findAll(pageable)
                .get()
                .map(personMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Long create(PersonDto personDto) {
        Person persistedPerson = personRepository
                .save(personMapper.dtoToEntity(personDto));
        return persistedPerson.getId();
    }

    @Override
    public void update(Long id, PersonDto personDto) {
        Person persistedPerson = handleFind(id);
        BeanUtils.copyProperties(personDto, persistedPerson);
        persistedPerson.setId(id);
        personRepository.save(persistedPerson);
    }

    @Override
    public void updateSchedule(Long id, String time) {
        try {
            LocalTime localTime = LocalTime.parse(time);
            personRepository.updateScheduleTimeById(id, localTime);
        }catch (Exception ex){
            throw new TimeFormatNotSupportedException(time);
        }
    }

    @Override
    public void delete(Long id) {
        personRepository.delete(handleFind(id));
    }

    private Person handleFind(Object key){

        Optional<Person> personOptional = null;

        if (key instanceof Long){
            personOptional = personRepository.findById((Long) key);
        }

        if (key instanceof String){
            personOptional = personRepository.findByName((String) key);
        }

        if (personOptional.isEmpty()){
            throw new ResourceNotFoundException(key.toString());
        }

        return personOptional.get();

    }
}
