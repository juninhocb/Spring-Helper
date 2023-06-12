package com.carlosjr.microclient.external;

import okhttp3.ResponseBody;
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

    @GET("greetings/bypath/{name}")
    Call<ResponseBody> getGreeting(@Path("name") String name);

    @GET("greetings/byquery")
    Call<ResponseBody> getGreetingQuery(@Query("name") String nameToQuery);

}
