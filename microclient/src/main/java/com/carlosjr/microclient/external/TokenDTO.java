package com.carlosjr.microclient.external;


import com.google.gson.annotations.SerializedName;

public record TokenDTO(@SerializedName("access_token") String accessToken) {
}
