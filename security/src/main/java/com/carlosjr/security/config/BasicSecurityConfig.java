package com.carlosjr.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Profile("basic")
public class BasicSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/public").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager  userDetailsService() {
        UserDetails webUser =
                User.withDefaultPasswordEncoder()
                        .username(AppProperties.webClient.username())
                        .password(AppProperties.webClient.password())
                        .roles(AppProperties.webClient.roles())
                        .build();

        UserDetails androidUser =
                User.withDefaultPasswordEncoder()
                        .username(AppProperties.mobileAndroidClient.username())
                        .password(AppProperties.mobileAndroidClient.password())
                        .roles(AppProperties.mobileAndroidClient.roles())
                        .build();

        return new InMemoryUserDetailsManager(Arrays.asList(androidUser, webUser));
    }
}
