package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.TeamMembers;
import com.managesuccess_backend.ManageSuccess_backend.entity.TeamMembersKey;
import com.managesuccess_backend.ManageSuccess_backend.repositories.TeamMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamMembersService {

    @Autowired
    private TeamMembersRepository teamMembersRepository;

    // Create a new TeamMember
    public TeamMembers createTeamMember(TeamMembers teamMember) {
        return teamMembersRepository.save(teamMember);
    }

    // Get all TeamMembers
    public List<TeamMembers> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    // Delete a TeamMember by composite key
    public boolean deleteTeamMember(String userId, String teamId) {
        TeamMembersKey teamMembersKey = new TeamMembersKey(userId, teamId);
        if (teamMembersRepository.existsById(teamMembersKey)) {
            teamMembersRepository.deleteById(teamMembersKey);
            return true;
        }
        return false; // TeamMember not found
    }
}
