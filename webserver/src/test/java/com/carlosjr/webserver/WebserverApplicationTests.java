package com.carlosjr.webserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebserverApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void getPages() {
		String str = String.valueOf(restTemplate.getForEntity("http://localhost:8080/get-page/petterson.html", String.class));
		assertTrue(str.isEmpty());
	}

}
