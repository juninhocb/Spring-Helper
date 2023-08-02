package com.example.carlosjr.ooconcepts.builder;


import com.example.carlosjr.ooconcepts.model.ResponseDto;
import com.example.carlosjr.ooconcepts.model.ResponseDtoOld;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BuilderApp implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        ResponseDtoOld response =  webClientGetResponse("Parameter11");

        System.out.println("normal");
        System.out.println(response.getIdentity());
        System.out.println(response.getMessage());

        System.out.println("from builder");
        ResponseDto responseBuilder = webClientGetResponseBuilder("Parameter1");
        System.out.println(responseBuilder.getIdentity());
        System.out.println(responseBuilder.getMessage());
    }

    private ResponseDto webClientGetResponseBuilder(String param) {

        ResponseDto.ResponseDtoBuilder responseDto = ResponseDto.builder();

        responseDto.identity(UUID.randomUUID().toString());

        if (param.equals("Parameter1")){
            responseDto.message("Is parameter 1");
        } else {
            responseDto.message("Is not parameter 1");
        }

        return responseDto.build();
    }

    private ResponseDtoOld webClientGetResponse(String param) {
        ResponseDtoOld responseDtoOld = new ResponseDtoOld();

        responseDtoOld.setIdentity(UUID.randomUUID().toString());
        if (param.equals("Parameter1")){
            responseDtoOld.setMessage("Is parameter 1");
        } else {
            responseDtoOld.setMessage("Is not parameter 1");
        }

        return responseDtoOld;

    }
}
