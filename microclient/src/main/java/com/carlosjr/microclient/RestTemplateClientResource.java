package com.carlosjr.microclient;

import com.carlosjr.microclient.external.GetTokenDTO;
import okhttp3.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import retrofit2.Response;

@RestController
@RequestMapping(value = "/rest-template")
public class RestTemplateClientResource {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MicroclientBaseProperties microclientBaseProperties;

    @Autowired
    MicroclientApiProperties microclientApiProperties;

    private MultiValueMap convertTokenDTOToJson(GetTokenDTO getTokenDTO) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", getTokenDTO.grant_type());
        requestBody.add("username", getTokenDTO.username());
        requestBody.add("password", getTokenDTO.password());
        requestBody.add("client", getTokenDTO.client());
        return requestBody;
    }
    @GetMapping("/get-token")
    public ResponseEntity<String> getToken(){
        GetTokenDTO getTokenDTO = new GetTokenDTO(
                microclientApiProperties.clientId(),
                microclientApiProperties.grantType(),
                microclientApiProperties.username(),
                microclientApiProperties.password());
        String url = String.format("%s/oauth/token", microclientBaseProperties.getBaseUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(microclientApiProperties.clientBasicAuthUsername(), microclientApiProperties.clientBasicAuthPassword());
        ResponseEntity<String> response = restTemplate.postForEntity(url, new HttpEntity<>(convertTokenDTOToJson(getTokenDTO), headers), String.class);
        return response;
    }

    @GetMapping("/bypath/{name}")
    public ResponseEntity<String> getHello(@PathVariable String name){
        String url = String.format("%s/greetings/bypath/%s", microclientBaseProperties.getBaseUrl(), name);
        ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
        return response;
    }

    @GetMapping("/byquery")
    public ResponseEntity<String> getHelloWithQuery(@RequestParam(value = "name", defaultValue = "unknown", required = false) String name){
        String url = String.format("%s/greetings/byquery?name=%s", microclientBaseProperties.getBaseUrl(), name);
        ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
        return response;
    }

}
