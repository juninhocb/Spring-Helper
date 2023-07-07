package com.carlosjr.test.dev.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Dev2ServiceImpl implements Dev2Service {
    private final Dev2ServiceFeignClient dev2ServiceFeignClient;
    public Dev2ServiceImpl(Dev2ServiceFeignClient dev2ServiceFeignClient) {
        this.dev2ServiceFeignClient = dev2ServiceFeignClient;
    }

    @Override
    public String getNameMessage(String name) {

        ResponseEntity<String> responseEntity = dev2ServiceFeignClient
                .getMessageFromDev2(name);

        return responseEntity.getBody();
    }
}
