package com.example.carlosjr.springwebmvc.resources;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyResourceTest {

    @Autowired
    TestRestTemplate restTemplate;

    private final String BASE_URL = "/api/v1";

    @Test
    void restfullEndpoint() {

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(BASE_URL + "/stub", String.class);

        assertThat(getResponse).isNotNull();

        System.out.println(getResponse); //full response

        System.out.println(getResponse.getBody()); //body
    }

    @Test
    void endpointByRequestMapping() {

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(BASE_URL + "/request-mapping", String.class);

        assertThat(getResponse).isNotNull();

        System.out.println(getResponse); //full response

        System.out.println(getResponse.getBody()); //body
    }

    @Test
    void changeStatusCode() {
        ResponseEntity<Void> getResponse = restTemplate
                .getForEntity(BASE_URL + "/change-status-code", Void.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    void stub2() {
        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(BASE_URL + "/stub2", String.class);

        assertThat(getResponse).isNotNull();

        System.out.println(getResponse); //full response

        System.out.println(getResponse.getBody()); //body
    }

    @Test
    void getPost() {
        ResponseEntity<Void> getResponse = restTemplate
                .postForEntity(BASE_URL + "/post", null ,Void.class);

        URI uri = getResponse.getHeaders().getLocation();
        assertThat(uri).isNotNull();

        System.out.println(getResponse); //full response

        System.out.println(uri.getPath()); //uri
    }

    @Test
    void getPut() {

        ResponseEntity<String> getPutResponse = restTemplate
                .exchange(BASE_URL + "/put", HttpMethod.PUT, null, String.class);

        assertThat(getPutResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println("Get headers: " + getPutResponse.getHeaders());

    }

    @Test
    void getDeleteWithCookie() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=12324");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> getDeleteResponse = restTemplate
                .exchange(BASE_URL + "/delete",
                        HttpMethod.DELETE, requestEntity, String.class);

        assertThat(getDeleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println("Get body: " + getDeleteResponse.getBody());
    }

    @Test
    void getPathAndCookieVariables() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "test=petterson");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> getResponse = restTemplate
                .exchange(BASE_URL + "/blabla",
                        HttpMethod.GET, requestEntity, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(getResponse.getBody());

    }

    @Test
    void getRequestParameters() {

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(BASE_URL + "?petterson=pet", String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(getResponse.getBody());

    }

    @Test
    void getRequestBody() {

        MyDTO myDTO = new MyDTO("John Green", "Blumenau-SC");
        ResponseEntity<String> getResponse = restTemplate
                .postForEntity(BASE_URL + "/request-body", myDTO, String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(getResponse.getBody());

    }

    @Test
    void canExcept() {

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity(BASE_URL + "/can-except?exception=test", String.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Test
    void getRequiredHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("test", "Peter Blue");
        HttpEntity<?> requestHttp = new HttpEntity<>(headers);
        ResponseEntity<String> getResponse = restTemplate
                .exchange(BASE_URL + "/header", HttpMethod.GET, requestHttp, String.class);

        assertThat(getResponse).isNotNull();

        System.out.println(getResponse.getBody());

    }
}