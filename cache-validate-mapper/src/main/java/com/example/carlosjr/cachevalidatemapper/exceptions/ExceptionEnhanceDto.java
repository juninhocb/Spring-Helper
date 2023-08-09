package com.example.carlosjr.cachevalidatemapper.exceptions;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionEnhanceDto(String message, LocalDateTime timestamp, String path, String className) {
}
