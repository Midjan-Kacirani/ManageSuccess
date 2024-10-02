package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.TeamDTO;
import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Team;
import com.managesuccess_backend.ManageSuccess_backend.mappers.TeamMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.TeamsRepository;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UsersService usersService;

    // Create a new team
    public TeamDTO createTeam(TeamDTO team) {

        UserDTO teamLeadDTO = usersService.getUserById(team.getTeamLeadId());
        team.setTeamLead(teamLeadDTO);
        return teamMapper.toDTO(teamsRepository.save(teamMapper.toTeamEntity(team)));

    }

    // Get a team by ID
    public TeamDTO getTeamById(String teamId) {
        Optional<Team> teamOptional = teamsRepository.findById(teamId);
        return teamOptional.map(team -> teamMapper.toDTO(team)).orElse(null);
    }

    // Get all teams
    public List<TeamDTO> getAllTeams() {
        return teamsRepository.findAll().stream().map(teamMapper::toDTO).collect(Collectors.toList());
    }

    // Update a team
    public TeamDTO updateTeam(String teamId, TeamDTO updatedTeam) {
        Optional<Team> existingTeam = teamsRepository.findById(teamId);
        if (existingTeam.isPresent()) {
            TeamDTO toBeUpdated = teamMapper.toDTO(existingTeam.get());
            UserDTO teamLead = null;
            if(!Utilities.isNullOrEmpty(updatedTeam.getTeamId())) teamLead = usersService.getUserById(updatedTeam.getTeamLeadId());
            if(!Utilities.isNullOrEmpty(updatedTeam.getTeamName())) toBeUpdated.setTeamName(updatedTeam.getTeamName());
            if(!Utilities.isNullOrEmpty(updatedTeam.getTeamDescription())) toBeUpdated.setTeamDescription(updatedTeam.getTeamDescription());
            if(teamLead != null) toBeUpdated.setTeamLead(teamLead);
            return teamMapper.toDTO(teamsRepository.save(teamMapper.toTeamEntity(toBeUpdated)));
        }
        return null; // Team not found
    }

    // Delete a team by ID
    public boolean deleteTeam(String teamId) {
        if (teamsRepository.existsById(teamId)) {
            teamsRepository.deleteById(teamId);
            return true;
        }
        return false; // Team not found
    }
}

