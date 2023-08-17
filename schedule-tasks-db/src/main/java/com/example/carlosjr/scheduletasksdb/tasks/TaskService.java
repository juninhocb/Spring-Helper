package com.example.carlosjr.scheduletasksdb.tasks;

import org.springframework.stereotype.Component;

@Component
public class TaskService {

    //@Scheduled(fixedRate =  1000)
    public void taskScheduled(){
        System.out.println("Ok!");
    }


}
