package com.example.springsecurityfilters.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Users (
    String username,
    String password,
    String name
){ }
