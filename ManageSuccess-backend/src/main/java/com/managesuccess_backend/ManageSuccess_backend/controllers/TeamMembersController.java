package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.TeamMembers;
import com.managesuccess_backend.ManageSuccess_backend.services.TeamMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team-members")
public class TeamMembersController {

    @Autowired
    private TeamMembersService teamMembersService;

    // Create a new TeamMember
    @PostMapping
    public ResponseEntity<TeamMembers> createTeamMember(@RequestBody TeamMembers teamMember) {
        TeamMembers createdTeamMember = teamMembersService.createTeamMember(teamMember);
        return new ResponseEntity<>(createdTeamMember, HttpStatus.CREATED);
    }

    // Get all TeamMembers
    @GetMapping
    public ResponseEntity<List<TeamMembers>> getAllTeamMembers() {
        List<TeamMembers> teamMembers = teamMembersService.getAllTeamMembers();
        return new ResponseEntity<>(teamMembers, HttpStatus.OK);
    }

    // Delete a TeamMember by composite key
    @DeleteMapping("/{teamId}/{userId}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable String teamId, @PathVariable String userId) {
        boolean isDeleted = teamMembersService.deleteTeamMember(userId, teamId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
