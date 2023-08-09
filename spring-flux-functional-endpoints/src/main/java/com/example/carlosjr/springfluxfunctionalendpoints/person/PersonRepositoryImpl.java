package com.example.carlosjr.springfluxfunctionalendpoints.person;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public Mono<Person> createPerson(Mono<Person> personMono) {
        return Mono.just(getPerson());
    }

    @Override
    public Mono<Person> findById(Integer id) {

        if (id <= 0){
            throw new RuntimeException();
        }

        if (id < 4){

            return getListPerson()
                    .filter(person -> person.getId() == id)
                    .next();

        } else {
            return null;
        }

    }

    @Override
    public Mono<Person> findByName(String name) {

        try {

            return getListPerson()
                    .filter(person -> person.getName().equals(name))
                    .next();

        }catch (Exception ex){
            return null;
        }

    }

    @Override
    public Flux<Person> findAll() {
        return getListPerson();
    }

    @Override
    public void deletePerson(Integer id) {
        System.out.println("Deleted!");
    }

    private Person getPerson(){
        return Person.builder().id(1).name("July White").build();
    }

    private Flux<Person> getListPerson(){
        Person person1 = Person.builder().id(1).name("July White").build();
        Person person2 = Person.builder().id(2).name("Dan Blue").build();
        Person person3 = Person.builder().id(3).name("John Red").build();
        return Flux.fromArray(new Person[] {person1,person2, person3});
    }


}
