package com.example.carlosjr.ooconcepts.builder;

import com.example.carlosjr.ooconcepts.model.PersonBuilder;
import com.example.carlosjr.ooconcepts.model.PersonOld;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BuilderPattern implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        PersonOld personOld = new PersonOld();
        personOld.setId(1L);
        personOld.setName("Petterson Green");

        PersonBuilder personBuilder = PersonBuilder.builder()
                .name("Petterson Black")
                .id(2L).build();

        //System.out.println("Person old: " + personOld.getName()  + " \n" + personOld.getId());

        //System.out.println("Person builder" + personBuilder);

    }
}
