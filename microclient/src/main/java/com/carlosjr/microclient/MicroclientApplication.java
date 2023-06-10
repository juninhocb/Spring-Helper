package com.carlosjr.microclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({MicroclientBaseProperties.class, MicroclientApiProperties.class})
@SpringBootApplication
public class MicroclientApplication {
	public static void main(String[] args) {SpringApplication.run(MicroclientApplication.class, args);}

}
