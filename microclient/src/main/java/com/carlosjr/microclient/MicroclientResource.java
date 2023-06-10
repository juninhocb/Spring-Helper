package com.carlosjr.microclient;

import com.carlosjr.microclient.external.ApiService;
import com.carlosjr.microclient.external.TokenDTO;
import okhttp3.Credentials;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RestController
@RequestMapping("/connect")
public class MicroclientResource {
    @Autowired
    private MicroclientApiProperties microclientApiProperties;

    @Autowired
    private ApiService service;

    @GetMapping(value = "/api")
    public ResponseEntity<String> getAndPrintAccessTokenFromApi(){
        String credentials = Credentials.basic(microclientApiProperties.clientBasicAuthUsername(), microclientApiProperties.clientBasicAuthPassword());
        Call<TokenDTO> call = service
                .getToken(credentials,
                        microclientApiProperties.grantType(),
                        microclientApiProperties.clientId(),
                        microclientApiProperties.username(),
                        microclientApiProperties.password());

        call.enqueue(new Callback<TokenDTO>() {
            @Override
            public void onResponse(Call<TokenDTO> call, Response<TokenDTO> response) {
                if (response.isSuccessful()){
                    TokenDTO tokenDTO = response.body();
                    if (tokenDTO != null) {
                        String accessToken = tokenDTO.accessToken();

                        System.out.println("Access Token: " + accessToken);
                    }
                } else {
                    System.out.println("Failed request error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenDTO> call, Throwable t) {
                System.out.println("Error with message: " + t.getMessage());
            }
        });

        return ResponseEntity.ok().body("Completed request!");

    }

    @GetMapping(value = "/test/{name}")
    public ResponseEntity<String> printHello(@PathVariable String name){
        Call<String> call = service.getGreeting(name);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    System.out.println("Hello mr/mrs " + response.body());
                } else {
                    System.out.println("Problem on request: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("An exception was erased on request: " + t.getMessage());
            }
        });
        return ResponseEntity.ok().body("ok");
    }

}
