package com.carlosjr.security.client;

public record Client(String username, String password, String[] roles) {
}
