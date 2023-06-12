package com.carlosjr.microclient;

import com.carlosjr.microclient.external.ApiService;
import com.carlosjr.microclient.external.TokenDTO;
import okhttp3.Credentials;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

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
    public ResponseEntity<String> retrieveTheHelloMessage(@PathVariable String name) throws IOException {
        Call<ResponseBody> call = service.getGreeting(name);
        Response<ResponseBody> callResponse = call.execute();
        return ResponseEntity.ok(callResponse.body().string());
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> printHelloByQuery(@RequestParam(value = "name", defaultValue = "unknown", required = false) String nameQuery){
        Call<ResponseBody> call = service.getGreetingQuery(nameQuery);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        System.out.println("Ok! " + response.body().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    System.out.println("Problem with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("An exception was occurred with message: "  + t.getMessage());
            }
        });
        return ResponseEntity.ok("OK with async");
    }
}
