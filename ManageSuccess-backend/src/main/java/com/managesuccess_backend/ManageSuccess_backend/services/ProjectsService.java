package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.ProjectDTO;
import com.managesuccess_backend.ManageSuccess_backend.dtos.TeamDTO;
import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Project;
import com.managesuccess_backend.ManageSuccess_backend.exceptions.MSException;
import com.managesuccess_backend.ManageSuccess_backend.mappers.ProjectMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.ProjectsRepository;
import com.managesuccess_backend.ManageSuccess_backend.services.GlobalObjectsService;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private GlobalObjectsService globalObjectsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TeamService teamService;

    // Create a new project
    @Transactional
    public ProjectDTO createProject(ProjectDTO projectDTO) throws MSException {
        //Find the user
        UserDTO userById = usersService.getUserById(projectDTO.getCreatedById());
        //Find the team
        // Convert DTO to entity
        TeamDTO teamById = teamService.getTeamById(projectDTO.getTeamId());

        projectDTO.setTeam(teamById);
        projectDTO.setCreatedBy(userById);

        Project project = projectMapper.toEntity(projectDTO);

        // Save the project
        Project savedProject = projectsRepository.save(project);

        // Create global object for the project
        globalObjectsService.createGlobalObjectFromEntity(savedProject);

        // Convert saved entity back to DTO
        return projectMapper.toDTO(savedProject);
    }

    // Get a project by ID
    public ProjectDTO getProjectById(String projectId) throws MSException {
        Optional<Project> project = projectsRepository.findById(projectId);

        // Handle if project is not found
        if (project.isEmpty()) {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " Project");
        }

        // Convert the entity to DTO
        return projectMapper.toDTO(project.get());
    }

    // Get all projects
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectsRepository.findAll();
        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Update a project by ID
    public ProjectDTO updateProject(String projectId, ProjectDTO projectDTO) throws MSException {
        Optional<Project> existingProject = projectsRepository.findById(projectId);

        if (existingProject.isPresent()) {

            ProjectDTO projectToUpdate = projectMapper.toDTO(existingProject.get());

            UserDTO userById = null;
            if(!Utilities.isNullOrEmpty(projectDTO.getCreatedById())) userById = usersService.getUserById(projectDTO.getCreatedById());
            TeamDTO teamDTO = null;
            if(!Utilities.isNullOrEmpty(projectDTO.getTeamId()))teamDTO = teamService.getTeamById(projectDTO.getTeamId());

            // Map updated fields from DTO
            if(userById != null) projectDTO.setCreatedBy(userById);
            if(teamDTO != null) projectDTO.setTeam(teamDTO);
            if (projectDTO.getProjectName() != null) projectToUpdate.setProjectName(projectDTO.getProjectName());
            if (projectDTO.getDescription() != null) projectToUpdate.setDescription(projectDTO.getDescription());
            if (projectDTO.getStatus() != null) projectToUpdate.setStatus(projectDTO.getStatus());
            if (projectDTO.getStartDate() != null) projectToUpdate.setStartDate(projectDTO.getStartDate());
            if (projectDTO.getEndDate() != null) projectToUpdate.setEndDate(projectDTO.getEndDate());

            // Save the updated project
            Project updatedProject = projectsRepository.save(projectMapper.toEntity(projectToUpdate));

            // Convert entity back to DTO
            return projectMapper.toDTO(updatedProject);
        } else {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " Project");
        }
    }

    // Delete a project by ID
    @Transactional
    public boolean deleteProject(String projectId) throws MSException {
        if (projectsRepository.existsById(projectId)) {
            projectsRepository.deleteById(projectId);
            globalObjectsService.deleteGlobalObjectByReferenceId(projectId);
            return true;
        } else {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " Project");
        }
    }
}
