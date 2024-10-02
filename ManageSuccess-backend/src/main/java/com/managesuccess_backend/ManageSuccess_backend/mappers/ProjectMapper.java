package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.ProjectDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Project;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeamMapper teamMapper;

    // Method to convert ProjectDTO to Projects entity
    public Project toEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }

        Project project = new Project();
        if(!Utilities.isNullOrEmpty(projectDTO.getProjectId()))project.setProjectId(projectDTO.getProjectId());  // Only used if the ID is required
        project.setProjectName(projectDTO.getProjectName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setTeam(teamMapper.toTeamEntity(projectDTO.getTeam()));  // Assuming team is retrieved and passed here
        project.setCreatedBy(userMapper.toEntity(projectDTO.getCreatedBy()));  // Assuming user is retrieved and passed here

        return project;
    }

    // Method to convert Projects entity to ProjectDTO
    public ProjectDTO toDTO(Project project) {
        if (project == null) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        projectDTO.setTeam(project.getTeam() != null ? teamMapper.toDTO(project.getTeam()) : null);  // Assuming a method to map Team to TeamDTO
        projectDTO.setCreatedBy(project.getCreatedBy() != null ? userMapper.toDTO(project.getCreatedBy()) : null);  // Assuming a method to map User to UserDTO

        return projectDTO;
    }
}
