package com.example.springiojackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringIoJacksonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIoJacksonApplication.class, args);
    }

}
@Component
@RequiredArgsConstructor
class JacksonLookup implements CommandLineRunner {
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Modules " + objectMapper.getRegisteredModuleIds());
        System.out.println("Is fail on unknown properties: " + objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }
}
