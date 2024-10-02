package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.TeamDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Team;
import com.managesuccess_backend.ManageSuccess_backend.services.TeamService;
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
    private TeamService teamsService;

    // Create a new team
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO team) {
        TeamDTO createdTeam = teamsService.createTeam(team);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    // Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable String teamId) {
        TeamDTO team = teamsService.getTeamById(teamId);
        if(team != null) return new ResponseEntity<>(team, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamsService.getAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // Update a team by ID
    @PutMapping("/{teamId}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable String teamId,
                                              @RequestBody(required = false) TeamDTO updatedTeam,
                                              @RequestParam(required = false) String teamName,
                                              @RequestParam(required = false) String teamDescription,
                                              @RequestParam(required = false) String teamLeadId) {
        if(updatedTeam == null){
            updatedTeam = new TeamDTO();
            updatedTeam.setTeamName(teamName);
            updatedTeam.setTeamDescription(teamDescription);
            updatedTeam.setTeamLeadId(teamLeadId);
        }
        TeamDTO updated = teamsService.updateTeam(teamId, updatedTeam);
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
