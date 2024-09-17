package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.Teams;
import com.managesuccess_backend.ManageSuccess_backend.repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {

    @Autowired
    private TeamsRepository teamsRepository;

    // Create a new team
    public Teams createTeam(Teams team) {
        return teamsRepository.save(team);
    }

    // Get a team by ID
    public Optional<Teams> getTeamById(String teamId) {
        return teamsRepository.findById(teamId);
    }

    // Get all teams
    public List<Teams> getAllTeams() {
        return teamsRepository.findAll();
    }

    // Update a team
    public Teams updateTeam(String teamId, Teams updatedTeam) {
        Optional<Teams> existingTeam = teamsRepository.findById(teamId);
        if (existingTeam.isPresent()) {
            Teams teamToUpdate = existingTeam.get();
            teamToUpdate.setTeamName(updatedTeam.getTeamName());
            teamToUpdate.setDescription(updatedTeam.getDescription());
            teamToUpdate.setTeamLead(updatedTeam.getTeamLead());
            return teamsRepository.save(teamToUpdate);
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

