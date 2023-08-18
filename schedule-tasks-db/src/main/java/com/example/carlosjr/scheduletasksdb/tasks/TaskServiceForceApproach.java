package com.example.carlosjr.scheduletasksdb.tasks;

import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import com.example.carlosjr.scheduletasksdb.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskServiceForceApproach {

    private final PersonService personService;

    @Scheduled(fixedRate = 60000)
    public void scanSchedules(){

        LocalTime now = LocalDateTime
                .now()
                .toLocalTime()
                .withSecond(0)
                .withNano(0);

        Set<PersonDto> executablePeople =  personService.findAllNotPaging()
                .stream()
                .filter(person -> person
                        .getScheduleTime()
                        .withSecond(0)
                        .withNano(0)
                        .equals(now))
                .collect(Collectors.toSet());

        executablePeople.forEach(this::doTheJob);
    }

    private void doTheJob(PersonDto personDto){
        System.out.println("Executing job for: " + personDto.getName());
    }
}
