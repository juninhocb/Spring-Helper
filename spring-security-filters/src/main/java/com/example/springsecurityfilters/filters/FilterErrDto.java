package com.example.springsecurityfilters.filters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record FilterErrDto (
    String message,
    HttpStatus status ) {}
