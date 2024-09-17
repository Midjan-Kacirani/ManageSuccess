package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.Projects;
import com.managesuccess_backend.ManageSuccess_backend.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    // Create a new project
    @PostMapping
    public ResponseEntity<Projects> createProject(@RequestBody Projects project) {
        Projects createdProject = projectsService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Get a project by ID
    @GetMapping("/{projectId}")
    public ResponseEntity<Projects> getProjectById(@PathVariable String projectId) {
        Optional<Projects> project = projectsService.getProjectById(projectId);
        return project.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Projects>> getAllProjects() {
        List<Projects> projects = projectsService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Update a project by ID
    @PutMapping("/{projectId}")
    public ResponseEntity<Projects> updateProject(@PathVariable String projectId, @RequestBody Projects projectDetails) {
        Projects updatedProject = projectsService.updateProject(projectId, projectDetails);
        if (updatedProject != null) {
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a project by ID
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        boolean isDeleted = projectsService.deleteProject(projectId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
