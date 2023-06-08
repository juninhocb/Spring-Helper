package com.carlosjr.email;

import com.carlosjr.email.email.EmailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailTest {
    @Autowired
    TestRestTemplate restTemplate;
    @Test
    @DirtiesContext
    public void shouldStatusCodeBeCreatedForCorrectlyRequests(){
        EmailDTO emailDTO = new EmailDTO(
                3L,
                "juninhocb@hotmail.com",
                "Testando email service",
                "\"Boa tarde \nEste é o conteúdo do email. \natt\"",
                null);

        ResponseEntity<Void> response = restTemplate
                .postForEntity(
                    "/email/send",
                        emailDTO,
                        Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<String> response2 = restTemplate
                .getForEntity(response.getHeaders().getLocation(), String.class);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldResponseWithNotFoundWhenAResourceIsNotFound(){
        ResponseEntity<String> response2 = restTemplate
                .getForEntity("/email/find/84", String.class);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    public void shouldInvalidateRequestInDifferentScenarios(){
        EmailDTO emailDTO = new EmailDTO(
                3L,
                null,
                "Testando email service",
                "\"Boa tarde \nEste é o conteúdo do email. \natt\"",
                null);

        EmailDTO emailDTO2 = new EmailDTO(
                3L,
                "juninhocb@hotmail.com",
                "",
                "",
                null);

        EmailDTO emailDTO3 = new EmailDTO(
                3L,
                "teste",
                "Testando email service",
                "\"Boa tarde \nEste é o conteúdo do email. \natt\"",
                null);

        EmailDTO emailDTO4 = new EmailDTO(
                -3L,
                "teste@hotmail.com",
                "Testando email service",
                "\"Boa tarde \nEste é o conteúdo do email. \natt\"",
                null);

        ResponseEntity<Void> response = restTemplate
                .postForEntity(
                        "/email/send",
                        emailDTO,
                        Void.class);

        ResponseEntity<Void> response2 = restTemplate
                .postForEntity(
                        "/email/send",
                        emailDTO2,
                        Void.class);

        ResponseEntity<Void> response3 = restTemplate
                .postForEntity(
                        "/email/send",
                        emailDTO3,
                        Void.class);

        ResponseEntity<Void> response4 = restTemplate
                .postForEntity(
                        "/email/send",
                        emailDTO4,
                        Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }



}
