package com.carlosjr.microclient.external;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<TokenDTO> getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            @Field("client") String client,
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("devices/test/{name}")
    Call<String> getGreeting(@Path("name") String nameToGreet);

    @GET("devices/test")
    Call<String> getGreetingQuery(@Query("name") String nameToQuery);

}
