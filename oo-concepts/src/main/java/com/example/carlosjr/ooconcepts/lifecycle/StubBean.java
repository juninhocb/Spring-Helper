package com.example.carlosjr.ooconcepts.lifecycle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StubBean {

    private final PrototypeBean prototypeBean;


}
