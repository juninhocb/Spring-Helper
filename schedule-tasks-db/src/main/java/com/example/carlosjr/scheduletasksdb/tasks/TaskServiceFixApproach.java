package com.example.carlosjr.scheduletasksdb.tasks;

import com.example.carlosjr.scheduletasksdb.rest.dtos.PersonDto;
import com.example.carlosjr.scheduletasksdb.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Approach to avoid database queries along the day
 */
//@Component
@RequiredArgsConstructor
@Slf4j
public class TaskServiceFixApproach {

    private final PersonService personService;
    private static Set<TaskDto> tasksToExecute = new HashSet<>();

    @Scheduled(cron = "0 24 9 * * *")
    public void scheduleReader(){

        log.info("Start scan schedule times");

        Set<PersonDto> people = personService.findAllNotPaging();

        tasksToExecute = people
                .stream()
                .map(person-> TaskDto
                        .builder()
                        .scheduleTime(person.getScheduleTime())
                        .id(person.getId())
                        .canExecute(true)
                        .build())
                .collect(Collectors.toSet());

        log.info("Finish scan");

    }

    @Scheduled(fixedRate = 60000)
    public void scanTaskSet(){

        log.info("Scan task set starts!");

        LocalTime now = LocalDateTime.now().toLocalTime();

        log.info("Now is: " + now);

        //Sync approach
        /*for (TaskDto task : tasksToExecute){
            log.info("Task detail schedule time: " + task.getScheduleTime());
            if (task.getScheduleTime().isBefore(now) && task.getCanExecute()){
                doTheJob(task);
            }
        }*/

        //Async approach
        tasksToExecute.forEach(task -> {
            log.info("Task detail schedule time: " + task.getScheduleTime());
            if (task.getScheduleTime().isBefore(now) && task.getCanExecute()){
                doTheJob(task);
            }
        });

        log.info("Finish scan task set!");
    }

    private void doTheJob(TaskDto taskDto){

        log.info("Execute job for this task with id: " + taskDto.getId());
        updateTaskExecutionStatus(taskDto.getId());
    }

    private void updateTaskExecutionStatus(Long taskId) {
        tasksToExecute.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .ifPresent(task -> task.setCanExecute(false));
    }




}
