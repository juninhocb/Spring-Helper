package com.example.springsecurityfilters.filters;

import com.example.springsecurityfilters.token.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MyFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null){
            System.out.println("Authorization is: " + authHeader);
            Authentication authentication = TokenUtil.decodeToken(request);
            if (authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                FilterErrDto err = FilterErrDto
                        .builder()
                        .message("User is not authorized for this system")
                        .status(HttpStatus.UNAUTHORIZED)
                        .build();
                response.setStatus(err.status().value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().print(objectMapper.writeValueAsString(err));
                response.getWriter().flush();
                return;

            }
        }
        System.out.println("Will pass request...");
        filterChain.doFilter(request, response);
    }
}
