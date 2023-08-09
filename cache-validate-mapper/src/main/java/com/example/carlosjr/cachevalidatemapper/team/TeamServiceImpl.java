package com.example.carlosjr.cachevalidatemapper.team;

import com.example.carlosjr.cachevalidatemapper.exceptions.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    @Override
    public TeamDto getTeamById(Integer id) {
        return teamMapper.entityToDto(handleFind(id));
    }

    @Override
    public TeamDto getTeamByName(String name) {
        return teamMapper.entityToDto(handleFind(name));
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::entityToDto)
                .toList();
    }

    @Override
    public Integer createNewTeam(TeamDto teamDto) {
        Team persitedTeam =  teamRepository.save(teamMapper.dtoToEntity(teamDto));
        return persitedTeam.getId();
    }

    @Override
    public void deleteTeam(Integer id) {
        teamRepository.delete(handleFind(id));
    }

    private Team handleFind(Object key){
        Optional<Team> teamOpt = null;

        if (key instanceof Integer){
            teamOpt = teamRepository.findById(((Integer) key).intValue());
        }

        if (key instanceof String){
            teamOpt = teamRepository.findByName(key.toString());
        }

        if (teamOpt.isEmpty()){
            throw new TeamNotFoundException("The resource was not found. " + key);
        }

        return  teamOpt.get();
    }
}
