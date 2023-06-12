package com.carlosjr.microclient.external;

public record GetTokenDTO(String client, String grant_type, String username, String password) {}
