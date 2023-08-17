package com.example.carlosjr.scheduletasksdb.rest.controller;

import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import com.example.carlosjr.scheduletasksdb.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{personId}/id")
    private ResponseEntity<PersonDto> findById(@PathVariable(name = "personId") Long id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }
    @GetMapping("/{personName}/name")
    private ResponseEntity<PersonDto> findByName(@PathVariable(name = "personName") String name){
        return new ResponseEntity<>(personService.findByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<PersonDto>> findAll(Pageable pageAble){

        Set<PersonDto> people = personService.findAll(
            PageRequest.of(pageAble.getPageNumber(), pageAble.getPageSize(), pageAble.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));

        return new ResponseEntity<>(people , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid PersonDto personDto,
                                       UriComponentsBuilder ucb){

        URI resourcePath = ucb
                .path("/api/v1/person/{id}")
                .buildAndExpand(personService.create(personDto))
                .toUri();

        return ResponseEntity.created(resourcePath).build();

    }

    @PutMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid PersonDto personDto,
                       @PathVariable(name = "personId") Long id){
        personService.update(id, personDto);
    }

    @PutMapping("/{personId}/settime")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setScheduleTime(@PathVariable(name = "personId") Long id,
                                @RequestParam(name = "time") String time){
        personService.updateSchedule(id, time);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "personId") Long id){
        personService.delete(id);
    }

}
