package com.example.carlosjr.ooconcepts.lifecycle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LifeCycleInspect implements CommandLineRunner {

    private final ConfigurableApplicationContext context;

    public LifeCycleInspect(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {

        boolean isSingleton = context
                .getBeanFactory().isSingleton("lifeCycleBean");

        //System.out.println("Is singleton? " + isSingleton);


    }
}
