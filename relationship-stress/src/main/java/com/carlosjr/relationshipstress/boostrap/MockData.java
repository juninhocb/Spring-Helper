package com.carlosjr.relationshipstress.boostrap;

import com.carlosjr.relationshipstress.models.Address;
import com.carlosjr.relationshipstress.models.User;
import com.carlosjr.relationshipstress.repository.AddressRepository;
import com.carlosjr.relationshipstress.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class MockData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {

        Address a1 = Address.builder()
                .name("Luther town")
                .build();

        Address a2 = Address.builder()
                .name("King place")
                .build();

        addressRepository.saveAll(Arrays.asList(a1,a2));

        User u1 = User.builder()
                .name("Jon green")
                .address(a1)
                .build();


        User u2 = User.builder()
                .name("Peter brown")
                .address(a1)
                .build();

        User u3 = User.builder()
                .name("Steven blue")
                .address(a2)
                .build();

        userRepository.saveAll(Arrays.asList(u1,u2,u3));

        log.info("Loaded data, repository size = " + userRepository.count());


    }
}
