package com.carlosjr.test.dev.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "dev2")
public interface Dev2ServiceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/{myNameaaa}/dev2")
    ResponseEntity<String> getMessageFromDev2(@PathVariable(name = "myNameaaa") String name);

}
