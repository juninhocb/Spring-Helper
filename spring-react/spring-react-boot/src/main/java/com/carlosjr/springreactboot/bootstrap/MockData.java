package com.carlosjr.springreactboot.bootstrap;

import com.carlosjr.springreactboot.models.UserLogin;
import com.carlosjr.springreactboot.repositories.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MockData implements CommandLineRunner {

    private final UserLoginRepository userLoginRepository;

    @Override
    public void run(String... args) throws Exception {

        userLoginRepository.deleteAll();

        userLoginRepository.saveAll(createUsers());

        log.info("Users created successfully.");
    }

    private List<UserLogin> createUsers(){
        UserLogin u1 = UserLogin.builder().username("john-green@example.com").password("password").build();
        UserLogin u2 = UserLogin.builder().username("ted-black@example.com").password("stub-password").build();
        return Arrays.asList(u1,u2);

    }

}
