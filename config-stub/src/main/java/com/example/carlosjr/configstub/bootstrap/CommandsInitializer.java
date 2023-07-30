package com.example.carlosjr.configstub.bootstrap;

import com.example.carlosjr.configstub.config.SetterFakeDatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandsInitializer implements CommandLineRunner {

    @Autowired
    private SetterFakeDatasourceConfig setterFakeDatasourceConfig;

    @Autowired
    @Qualifier("getCustomFooProperty")
    private String getFoo;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(setterFakeDatasourceConfig.getUsername());
        System.out.println(setterFakeDatasourceConfig.getPassword());
        System.out.println(setterFakeDatasourceConfig.getUrl());
        System.out.println(getFoo);


    }
}
