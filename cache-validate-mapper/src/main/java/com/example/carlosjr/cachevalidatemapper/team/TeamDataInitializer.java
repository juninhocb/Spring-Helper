package com.example.carlosjr.cachevalidatemapper.team;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamDataInitializer implements CommandLineRunner {
    private final TeamService teamService;
    @Override
    public void run(String... args) throws Exception {

        mockData();
    }

    private void mockData(){

        TeamDto t1 = TeamDto.builder()
                .name("Palmeiras")
                .titles(127)
                .isBig(true)
                .build();

        TeamDto t2 = TeamDto.builder()
                .name("Juventude")
                .titles(31)
                .isBig(false)
                .build();

        TeamDto t3 = TeamDto.builder()
                .name("Cruzeiro")
                .titles(107)
                .isBig(true)
                .build();

        teamService.createNewTeam(t1);
        teamService.createNewTeam(t2);
        teamService.createNewTeam(t3);

    }
}
