package com.carlosjr.example.secproject.bootstrap;

import com.carlosjr.example.secproject.user.User;
import com.carlosjr.example.secproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MockInit implements CommandLineRunner {
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0){
            User u1 = User.builder().name("John Green").password("myPass").build();
            userRepository.save(u1);
        }
    }
}
