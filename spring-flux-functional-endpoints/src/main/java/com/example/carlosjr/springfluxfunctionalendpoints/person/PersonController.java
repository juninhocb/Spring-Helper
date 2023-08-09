package com.example.carlosjr.springfluxfunctionalendpoints.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    public Mono<ServerResponse> createPerson(ServerRequest request){
        Mono<Person> personFromRequest = request.bodyToMono(Person.class);
        System.out.println(personFromRequest);
        return personRepository.createPerson(personFromRequest)
                .flatMap(persistedPerson -> {
                    return ServerResponse.ok()
                            .header("location", PersonRouterConfig.BASE_URL+"/"+persistedPerson.getId())
                            .build();
                });
    }

    public Mono<ServerResponse> getPersonById(ServerRequest request){
        Integer personId = Integer.valueOf(request.pathVariable("personId"));
        //simulate of query param
        //Boolean isFiltered = Boolean.valueOf(request.queryParam("isFiltered").orElse("false"));

        return personRepository.findById(personId)
                .flatMap(person -> {
                    return ServerResponse.ok().bodyValue(person);
                }).switchIfEmpty(ServerResponse.notFound().build());

    }


}
