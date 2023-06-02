package com.carlosjr.security.config;

import com.carlosjr.security.client.Client;

public class AppProperties {
    public static Client webClient = new Client("Web", "password", new String[] {"READ"});
    public static Client mobileAndroidClient = new Client("Android", "password2", new String[]{"READ, WRITE"});
}
