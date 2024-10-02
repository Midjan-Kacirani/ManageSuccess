package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.ProjectDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Project;
import com.managesuccess_backend.ManageSuccess_backend.enums.Status;
import com.managesuccess_backend.ManageSuccess_backend.exceptions.MSException;
import com.managesuccess_backend.ManageSuccess_backend.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    // Create a new project
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) throws MSException {
        ProjectDTO createdProject = projectsService.createProject(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Get a project by ID
    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable String projectId) throws MSException {
        ProjectDTO projectDTO = projectsService.getProjectById(projectId);
        if(projectDTO == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(projectDTO, HttpStatus.NOT_FOUND);
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectsService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable String projectId,
                                                    @RequestBody(required = false) ProjectDTO projectDetails,
                                                    @RequestParam(required = false) String projectName,
                                                    @RequestParam(required = false) String description,
                                                    @RequestParam(required = false) Status status,
                                                    @RequestParam(required = false) LocalDateTime startDate,
                                                    @RequestParam(required = false) LocalDateTime endDate,
                                                    @RequestParam(required = false) String teamId,
                                                    @RequestParam(required = false) String createdById) throws MSException {
        // If no request body is provided, initialize a new ProjectDTO and populate with query params
        if (projectDetails == null) {
            projectDetails = new ProjectDTO();
            projectDetails.setProjectName(projectName);
            projectDetails.setDescription(description);
            projectDetails.setStatus(status);
            projectDetails.setStartDate(startDate);
            projectDetails.setEndDate(endDate);
            projectDetails.setTeamId(teamId);
            projectDetails.setCreatedById(createdById);
        }

        // Call the service to update the project
        ProjectDTO updatedProject = projectsService.updateProject(projectId, projectDetails);

        // Return appropriate response based on whether the update was successful
        if (updatedProject != null) {
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a project by ID
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) throws MSException {
        boolean isDeleted = projectsService.deleteProject(projectId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
