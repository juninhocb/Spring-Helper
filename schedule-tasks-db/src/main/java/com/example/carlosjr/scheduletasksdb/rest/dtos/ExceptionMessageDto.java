package com.example.carlosjr.scheduletasksdb.rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionMessageDto {

    private String timestamp;
    @JsonProperty(value = "status")
    private Integer statusCode;
    private String message;
    private String path;
    @JsonProperty(value = "class")
    private String javaClass;

}
