package com.example.carlosjr.cachevalidatemapper.team;


import com.example.carlosjr.cachevalidatemapper.exceptions.TeamNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeamServiceImplTest {

    @Autowired
    @Qualifier("teamServiceImpl")
    TeamService teamService;
    @Test
    @Order(2)
    void getTeamById() {
        TeamDto teamTest = teamService.getTeamById(1);
        assertThat(teamTest).isNotNull();
    }

    @Test
    @Order(1)
    void getTeamByName() {
        TeamDto teamTest = teamService.getTeamByName("Palmeiras");
        assertThat(teamTest).isNotNull();
    }

    @Test
    @Order(3)
    void getAllTams() {
        List<TeamDto> teams = teamService.getAllTeams();
        assertThat(teams.size()).isEqualTo(3);
    }

    @Test
    @DirtiesContext
    @Order(4)
    void createNewTeam() {
        TeamDto teamDto = TeamDto.builder()
                .name("Cruz Azul")
                .titles(35)
                .isBig(false)
                .build();

        Integer createdId = teamService.createNewTeam(teamDto);
        assertThat(createdId).isNotNull();
    }

    @Test
    @Order(5)
    void deleteTeam() {
        TeamDto teamTest = teamService.getTeamById(1);
        assertThat(teamTest).isNotNull();
        teamService.deleteTeam(teamTest.id());
        assertThrows(TeamNotFoundException.class, () -> {
            teamService.getTeamById(1);
        });
    }
}