package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.TeamDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    @Autowired
    private UserMapper userMapper;

    public TeamDTO toDTO(Team team){
    TeamDTO teamDTO = new TeamDTO();
    teamDTO.setTeamId(team.getTeamId());
    teamDTO.setTeamName(team.getTeamName());
    teamDTO.setTeamDescription(team.getDescription());
    teamDTO.setTeamLead(userMapper.toDTO(team.getTeamLead()));
    return teamDTO;
    }

    public Team toTeamEntity(TeamDTO teamDTO){
        Team team = new Team();
        if(teamDTO.getTeamId() != null) team.setTeamId(teamDTO.getTeamId());
        team.setTeamName(teamDTO.getTeamName());
        team.setDescription(teamDTO.getTeamDescription());
        team.setTeamLead(userMapper.toEntity(teamDTO.getTeamLead()));
        return team;
    }
}
