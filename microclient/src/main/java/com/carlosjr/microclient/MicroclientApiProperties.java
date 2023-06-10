package com.carlosjr.microclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("microclient.api")
public record MicroclientApiProperties(
        @DefaultValue("client") String clientId,
        @DefaultValue("tester") String username,
        @DefaultValue("password") String password,
        @DefaultValue("refresh_token") String grantType,
        @DefaultValue("clientUsername") String clientBasicAuthUsername,
        @DefaultValue("ClientPassword") String clientBasicAuthPassword){
}
