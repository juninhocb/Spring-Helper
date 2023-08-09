package com.example.carlosjr.cachevalidatemapper.team;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class TeamMapperTest {

    @Autowired
    TeamMapper teamMapper;
    @Test
    void dtoToEntity() {
        Team teamFromMapper = teamMapper.dtoToEntity(getDto());
        assertThat(teamFromMapper.getName()).isEqualTo(getDto().name());
        assertThat(teamFromMapper.getIsBig()).isEqualTo(getDto().isBig());
        assertThat(teamFromMapper.getTitles()).isEqualTo(getDto().titles());
    }

    @Test
    void entityToDto() {
        TeamDto teamDtoFromMapper = teamMapper.entityToDto(getEntity());
        assertThat(teamDtoFromMapper.name()).isEqualTo(getEntity().getName());
        assertThat(teamDtoFromMapper.titles()).isEqualTo(getEntity().getTitles());
        assertThat(teamDtoFromMapper.isBig()).isEqualTo(getEntity().getIsBig());
    }

    private TeamDto getDto(){
        return TeamDto.builder()
                .name("Cruz Azul")
                .titles(35)
                .isBig(false)
                .build();
    }

    private Team getEntity(){
        return Team.builder()
                .name("Cruz Azul")
                .titles(35)
                .isBig(false)
                .build();
    }
}