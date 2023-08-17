package com.example.carlosjr.scheduletasksdb.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    @Null
    private Long id;
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    @Positive
    private Integer age;
    @NotNull
    private Boolean enabled;
    @Email
    private String email;
    @JsonProperty(value = "schedule_time")
    private LocalTime scheduleTime;
}
