package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.TaskDTO;
import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Task;
import com.managesuccess_backend.ManageSuccess_backend.exceptions.MSException;
import com.managesuccess_backend.ManageSuccess_backend.mappers.TaskMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.TasksRepository;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private GlobalObjectsService globalObjectsService;

    // Create a new task
    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) throws MSException {
        // Validate and find the user assigned to the task
        UserDTO assignedUser = usersService.getUserById(taskDTO.getAssignedToId());
        if (assignedUser == null) {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " User");
        }

        // Set the assigned user
        taskDTO.setAssignedTo(assignedUser);

        // Validate and find the project associated with the task
        if (!Utilities.isNullOrEmpty(taskDTO.getProjectId())) {
            taskDTO.setProject(projectsService.getProjectById(taskDTO.getProjectId()));
        }

        // Convert DTO to entity
        Task task = taskMapper.toEntity(taskDTO);

        // Save the task
        Task savedTask = tasksRepository.save(task);

        // Create global object for the task
        globalObjectsService.createGlobalObjectFromEntity(savedTask);

        // Convert saved entity back to DTO
        return taskMapper.toDTO(savedTask);
    }

    // Get a task by ID
    public TaskDTO getTaskById(String taskId) throws MSException {
        Optional<Task> task = tasksRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " Task");
        }
        return taskMapper.toDTO(task.get());
    }

    // Get all tasks
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = tasksRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Update a task by ID
    public TaskDTO updateTask(String taskId, TaskDTO taskDTO) throws MSException {
        Optional<Task> existingTask = tasksRepository.findById(taskId);

        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();

            // Update assigned user
            if (!Utilities.isNullOrEmpty(taskDTO.getAssignedToId())) {
                UserDTO assignedUser = usersService.getUserById(taskDTO.getAssignedToId());
                taskDTO.setAssignedTo(assignedUser);
            }

            // Update project
            if (!Utilities.isNullOrEmpty(taskDTO.getProjectId())) {
                taskDTO.setProject(projectsService.getProjectById(taskDTO.getProjectId()));
            }

            // Update task fields
            if (!Utilities.isNullOrEmpty(taskDTO.getTaskName())) taskToUpdate.setTaskName(taskDTO.getTaskName());
            if (!Utilities.isNullOrEmpty(taskDTO.getDescription())) taskToUpdate.setDescription(taskDTO.getDescription());
            if (taskDTO.getStatus() != null) taskToUpdate.setStatus(taskDTO.getStatus());
            if (taskDTO.getPriority() != null) taskToUpdate.setPriority(taskDTO.getPriority());
            if (taskDTO.getStartDate() != null) taskToUpdate.setStartDate(taskDTO.getStartDate());
            if (taskDTO.getEndDate() != null) taskToUpdate.setEndDate(taskDTO.getEndDate());
            if (taskDTO.getDueDate() != null) taskToUpdate.setDueDate(taskDTO.getDueDate());

            // Save updated task
            Task updatedTask = tasksRepository.save(taskToUpdate);

            // Convert entity back to DTO
            return taskMapper.toDTO(updatedTask);
        } else {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " Task");
        }
    }

    // Delete a task by ID
    @Transactional
    public boolean deleteTask(String taskId) throws MSException {
        if (tasksRepository.existsById(taskId)) {
            tasksRepository.deleteById(taskId);
            globalObjectsService.deleteGlobalObjectByReferenceId(taskId);
            return true;
        } else {
            throw new MSException(MSException.RESOURCE_NOT_FOUND + " Task");
        }
    }
}
