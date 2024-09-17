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

    // Get a TeamMember by composite key
    @GetMapping("/{userId}/{teamId}")
    public ResponseEntity<TeamMembers> getTeamMemberById(@PathVariable String userId, @PathVariable String teamId) {
        Optional<TeamMembers> teamMember = teamMembersService.getTeamMemberById(userId, teamId);
        return teamMember.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all TeamMembers
    @GetMapping
    public ResponseEntity<List<TeamMembers>> getAllTeamMembers() {
        List<TeamMembers> teamMembers = teamMembersService.getAllTeamMembers();
        return new ResponseEntity<>(teamMembers, HttpStatus.OK);
    }

    // Update a TeamMember by composite key
    @PutMapping("/{userId}/{teamId}")
    public ResponseEntity<TeamMembers> updateTeamMember(@PathVariable String userId, @PathVariable String teamId, @RequestBody TeamMembers updatedTeamMember) {
        TeamMembers updated = teamMembersService.updateTeamMember(userId, teamId, updatedTeamMember);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a TeamMember by composite key
    @DeleteMapping("/{userId}/{teamId}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable String userId, @PathVariable String teamId) {
        boolean isDeleted = teamMembersService.deleteTeamMember(userId, teamId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
