package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.Projects;
import com.managesuccess_backend.ManageSuccess_backend.repositories.ProjectsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;
    @Autowired
    private GlobalObjectsService globalObjectsService;

    // Create a new project
    @Transactional
    public Projects createProject(Projects project) {
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedAt(LocalDateTime.now());
        Projects projectSaved = projectsRepository.save(project);
        globalObjectsService.createGlobalObjectFromEntity(projectSaved);
        return projectSaved;
    }

    // Get a project by ID
    public Optional<Projects> getProjectById(String projectId) {
        return projectsRepository.findById(projectId);
    }

    // Get all projects
    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    // Update a project by ID
    public Projects updateProject(String projectId, Projects projectDetails) {
        Optional<Projects> existingProject = projectsRepository.findById(projectId);
        if (existingProject.isPresent()) {
            Projects projectToUpdate = existingProject.get();
            projectToUpdate.setProjectName(projectDetails.getProjectName());
            projectToUpdate.setDescription(projectDetails.getDescription());
            projectToUpdate.setStatus(projectDetails.getStatus());
            projectToUpdate.setStartDate(projectDetails.getStartDate());
            projectToUpdate.setEndDate(projectDetails.getEndDate());
            projectToUpdate.setTeam(projectDetails.getTeam());
            projectToUpdate.setCreatedBy(projectDetails.getCreatedBy());
            projectToUpdate.setUpdatedAt(LocalDateTime.now());
            return projectsRepository.save(projectToUpdate);
        } else {
            return null;  // Project not found
        }
    }

    // Delete a project by ID
    @Transactional

    public boolean deleteProject(String projectId) {
        if (projectsRepository.existsById(projectId)) {
            projectsRepository.deleteById(projectId);
            globalObjectsService.deleteGlobalObjectByReferenceId(projectId);
            return true;
        }
        return false;
    }
}
