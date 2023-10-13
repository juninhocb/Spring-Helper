package com.example.springsoapservercf;

import jakarta.annotation.PostConstruct;
import localhost._8080.hisoap.Person;
import localhost._8080.hisoap.Type;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {

    private final Map<String, Person> persistedPeople = new HashMap<>();

    @PostConstruct
    public void mockRepository(){
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();

        p1.setId(1);
        p1.setAge(25);
        p1.setName("John Green");
        p1.setType(Type.ADMIN);

        p2.setId(2);
        p2.setAge(31);
        p2.setName("Peter Black");
        p2.setType(Type.MANAGER);

        p3.setId(3);
        p3.setAge(27);
        p3.setName("Anne Purple");
        p3.setType(Type.NORMAL);

        persistedPeople.put(p1.getName(), p1);
        persistedPeople.put(p2.getName(), p2);
        persistedPeople.put(p3.getName(), p3);
    }

    public Person getByName(String name){
        return persistedPeople.get(name);
    }

}
