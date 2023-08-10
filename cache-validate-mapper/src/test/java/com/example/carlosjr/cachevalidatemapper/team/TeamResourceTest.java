package com.example.carlosjr.cachevalidatemapper.team;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeamResourceTest {

    @Autowired
    TeamResource teamResource;
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TestRestTemplate restTemplate;
    final String BASE_URL = "/api/v1/team";
    @Test
    void getTeamById() {
        ResponseEntity<TeamDto> response = restTemplate
                .getForEntity(BASE_URL + "/1", TeamDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo("Palmeiras");
    }

    @Test
    void getTeamByName() {
        ResponseEntity<TeamDto> response = restTemplate
                .getForEntity(BASE_URL + "/find?name=Palmeiras", TeamDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo("Palmeiras");
    }

    @Test
    void getAllTeams() {
        ResponseEntity<List<TeamDto>> response = restTemplate
                .exchange(BASE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<TeamDto>>(){});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(3);
    }

    @Test
    @DirtiesContext
    void createNewTeam() {
        ResponseEntity<Void> createResponse = restTemplate.
                postForEntity(BASE_URL, getTeamDto() ,Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        URI resourceLocation = createResponse.getHeaders().getLocation();
        ResponseEntity<TeamDto> response = restTemplate
                .getForEntity(resourceLocation, TeamDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo("Penarol");
    }

    @Test
    void deleteTeam() {
        ResponseEntity<TeamDto> response = restTemplate
                .getForEntity(BASE_URL + "/1", TeamDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().name()).isEqualTo("Palmeiras");
        restTemplate.exchange(BASE_URL+"/1", HttpMethod.DELETE, null, Void.class);
        ResponseEntity<TeamDto> afterDeleteResponse = restTemplate
                .getForEntity(BASE_URL + "/1", TeamDto.class);
        assertThat(afterDeleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void testValidator(){
        for (int i=0; i < 7; i++){
            System.out.println("case: " +  i);
            ResponseEntity<Void> createResponse = restTemplate.
                    postForEntity(BASE_URL, getInvalidTeamDto(i) ,Void.class);
            assertThat(createResponse.getStatusCode()).isNotEqualTo(HttpStatus.CREATED);
        }
    }

    //just 4 my pruposes this test will be validated using sout in service class
    //to better test it, mock is required
    @Test
    @RepeatedTest(5)
    void cacheTest(){
        ResponseEntity<List<TeamDto>> response = restTemplate
                .exchange(BASE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<TeamDto>>(){});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(3);
    }

    private TeamDto getTeamDto(){
        return TeamDto.builder()
                .name("Penarol")
                .titles(120)
                .isBig(true)
                .build();
    }

    private TeamDto getInvalidTeamDto(Integer code){
        switch (code){
            case 0:
                return TeamDto.builder()
                        .titles(120)
                        .isBig(true)
                        .build();
            case 1:
                return TeamDto.builder()
                        .name("Pen")
                        .titles(120)
                        .isBig(true)
                        .build();
            case 2:
                return TeamDto.builder()
                        .name("Penarol")
                        .titles(-1)
                        .isBig(true)
                        .build();
            case 3:
                return TeamDto.builder()
                        .name("Penarol")
                        .isBig(true)
                        .build();
            case 4:
                return TeamDto.builder()
                        .name("Penarol")
                        .titles(120)
                        .build();
            case 5:
                return TeamDto.builder()
                        .id(4)
                        .name("Penarol")
                        .titles(120)
                        .isBig(true)
                        .build();
            case 6:
                return TeamDto.builder()
                        .name("Penarol6")
                        .titles(120)
                        .isBig(true)
                        .build();
        }

        return null;

    }
}