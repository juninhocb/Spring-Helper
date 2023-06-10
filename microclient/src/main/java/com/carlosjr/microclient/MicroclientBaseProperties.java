package com.carlosjr.microclient;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("microclient")
public class MicroclientBaseProperties {
    /**
     * URL of API
     */
    private final String baseUrl;
    public MicroclientBaseProperties(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public String getBaseUrl() {
        return baseUrl;
    }
}
