package com.example.carlosjr.cachevalidatemapper.bootstrap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class DigestTeam implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        MessageDigest digestSha256 = MessageDigest.getInstance("SHA-256");
        byte[] hash = digestSha256.digest(("palmeiras:team".getBytes("UTF-8")));
        String base64String = Base64.encodeBase64String(hash);
        System.out.println(base64String);

    }
}
