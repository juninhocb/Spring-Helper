package com.carlosjr.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Log4j2Application implements CommandLineRunner {
	private static final Logger logger = LogManager.getLogger(Log4j2Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Log4j2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Starting logger...");
	}


}
