package com.example.carlosjr.cachevalidatemapper.team;

import java.util.List;

public interface TeamService {

    TeamDto getTeamById(Integer id);
    TeamDto getTeamByName(String name);
    List<TeamDto> getAllTeams();
    Integer createNewTeam(TeamDto teamDto);
    void deleteTeam(Integer id);

}
