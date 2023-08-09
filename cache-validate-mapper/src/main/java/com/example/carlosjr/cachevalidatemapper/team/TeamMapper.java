package com.example.carlosjr.cachevalidatemapper.team;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeamMapper {
    Team dtoToEntity(TeamDto teamDto);
    TeamDto entityToDto(Team team);

}
