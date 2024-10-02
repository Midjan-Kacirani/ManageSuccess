package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.TaskDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Task;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    // Convert from Task entity to TaskDTO
    public TaskDTO toDTO(Task task) {
        if (task == null) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setAssignedTo(task.getAssignedTo() != null ? userMapper.toDTO(task.getAssignedTo()) : null);  // Map assigned user to UserDTO
        taskDTO.setProject(task.getProject() != null ?projectMapper.toDTO(task.getProject()) : null);  // Map project to ProjectDTO

        return taskDTO;
    }
    // Convert from TaskDTO to Task entity

    public Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }

        Task task = new Task();
        if (!Utilities.isNullOrEmpty(taskDTO.getTaskId()))
            task.setTaskId(taskDTO.getTaskId());  // Only set if the ID is provided
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setDueDate(taskDTO.getDueDate());
        task.setAssignedTo(userMapper.toEntity(taskDTO.getAssignedTo()));  // Map assigned user to User entity
        task.setProject(projectMapper.toEntity(taskDTO.getProject()));  // Map project to Project entity

        return task;
    }

}
