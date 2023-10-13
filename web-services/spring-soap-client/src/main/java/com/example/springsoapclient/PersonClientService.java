package com.example.springsoapclient;

import com.example.springsoapclient.ws.GetPersonRequest;
import com.example.springsoapclient.ws.GetPersonResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class PersonClientService extends WebServiceGatewaySupport {

    public GetPersonResponse getPerson(String name) {

        GetPersonRequest request = new GetPersonRequest();
        request.setName(name);

        GetPersonResponse response = (GetPersonResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/person", request, null);

        return response;
    }
}
