package com.example.carlosjr.scheduletasksdb.tasks;

import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private LocalTime scheduleTime;
    private Boolean canExecute;

}
