package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.Teams;
import com.managesuccess_backend.ManageSuccess_backend.services.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamsController {

    @Autowired
    private TeamsService teamsService;

    // Create a new team
    @PostMapping
    public ResponseEntity<Teams> createTeam(@RequestBody Teams team) {
        Teams createdTeam = teamsService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    // Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<Teams> getTeamById(@PathVariable String teamId) {
        Optional<Teams> team = teamsService.getTeamById(teamId);
        return team.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<Teams>> getAllTeams() {
        List<Teams> teams = teamsService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Update a team by ID
    @PutMapping("/{teamId}")
    public ResponseEntity<Teams> updateTeam(@PathVariable String teamId, @RequestBody Teams updatedTeam) {
        Teams updated = teamsService.updateTeam(teamId, updatedTeam);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a team by ID
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String teamId) {
        boolean isDeleted = teamsService.deleteTeam(teamId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
