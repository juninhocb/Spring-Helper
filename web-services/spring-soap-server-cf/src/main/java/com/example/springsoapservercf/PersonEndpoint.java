package com.example.springsoapservercf;

import localhost._8080.hisoap.GetPersonRequest;
import localhost._8080.hisoap.GetPersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PersonEndpoint {
    private final PersonRepository personRepository;
    @PayloadRoot(namespace = "http://localhost:8080/hisoap", localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(personRepository.getByName(request.getName()));
        return response;
    }
}
