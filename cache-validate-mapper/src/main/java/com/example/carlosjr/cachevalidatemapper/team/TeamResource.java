package com.example.carlosjr.cachevalidatemapper.team;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamResource {

    private final TeamService teamService;
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable(name = "teamId")Integer id){
        return ResponseEntity.ok().body(teamService.getTeamById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<TeamDto> getTeamByName(@RequestParam(value = "name") String name){
        return ResponseEntity.ok().body(teamService.getTeamByName(name));
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams(){
        return ResponseEntity.ok().body(teamService.getAllTeams());
    }

    @PostMapping
    public ResponseEntity<Void> createNewTeam(@RequestBody @Valid TeamDto teamDto,
                                              UriComponentsBuilder ucb){
        Integer persistedId = teamService.createNewTeam(teamDto);
        URI resourceUrl = ucb
                .path("/api/v1/team/{id}")
                .buildAndExpand(persistedId)
                .toUri();
        return ResponseEntity.created(resourceUrl).build();
    }

    @DeleteMapping("/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable(name = "teamId")Integer id){
        teamService.deleteTeam(id);
    }


}
