package com.example.demoaop.demo;

import lombok.Builder;

@Builder
public record MyObject(Integer id, String name, Boolean isAny) { }
