package com.example.springsecurityfilters.token;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtil {

    public static Authentication decodeToken(HttpServletRequest request) {
        if (request.getHeader("Authorization").equals("Bearer myBeaterToken")){
            return new UsernamePasswordAuthenticationToken("me", null, Collections.emptyList());
        }
        return null;
    }

}
