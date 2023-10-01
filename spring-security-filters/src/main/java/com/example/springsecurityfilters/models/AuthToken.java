package com.example.springsecurityfilters.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthToken (
    @JsonProperty("token") String keyToken,
    @JsonProperty("expiration_time") Integer expirationTime
){ }
