package com.carlosjr.test.dev.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Dev2ServiceImplTest {
    @Autowired
    private Dev2Service dev2Service;
    @Test
    void getNameMessage() {

        String nameMessage = dev2Service.getNameMessage("John test");
        System.out.println("Message received... " + nameMessage);
        assertThat(nameMessage).isEqualTo("Your name is: John test");


    }
}