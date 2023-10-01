package com.example.springsecurityfilters.token;

import com.example.springsecurityfilters.models.AuthToken;
import com.example.springsecurityfilters.models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    /*@Value("${my.props.expiration-time}")
    private static String expirationTime;*/

    private static final String EMITTER = "My Application";
    private static final String TOKEN_HEADER = "Bearer ";

    private static final String TOKEN_KEY = "9291616d876f4d1fb694ebaeb4c63f58";
    private static final long ONE_SECOND = 1000;
    private static final long ONE_MINUTE = 60*ONE_SECOND;


    public static AuthToken encodeToken(Users u) {

        Key key = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String tokenJWT = Jwts.builder()
                .setSubject(u.username())
                .setIssuer(EMITTER)
                .setExpiration(new Date(System.currentTimeMillis() + ONE_MINUTE))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return AuthToken
                .builder()
                .expirationTime(Integer.parseInt("1000"))
                .keyToken(TOKEN_HEADER + tokenJWT)
                .build();
    }
    public static Authentication decodeToken(HttpServletRequest request) {

        try {
            String jwtToken = request
                                .getHeader("Authorization")
                                .replace(TOKEN_HEADER, "");

            Jws<Claims> jwsClaims = Jwts
                                .parserBuilder()
                                .setSigningKey(TOKEN_KEY.getBytes())
                                .build()
                                .parseClaimsJws(jwtToken);

            String username = jwsClaims.getBody().getSubject();
            String emitter = jwsClaims.getBody().getIssuer();
            Date expiration = jwsClaims.getBody().getExpiration();
            if (username.equals("me") &&
                emitter.equals(EMITTER) &&
                expiration.after(new Date(System.currentTimeMillis()))){
                return new UsernamePasswordAuthenticationToken("me", null, Collections.emptyList());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
