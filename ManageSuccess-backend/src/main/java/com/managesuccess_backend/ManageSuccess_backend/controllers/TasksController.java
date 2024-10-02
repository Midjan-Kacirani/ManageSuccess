package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.TaskDTO;
import com.managesuccess_backend.ManageSuccess_backend.enums.Priority;
import com.managesuccess_backend.ManageSuccess_backend.enums.Status;
import com.managesuccess_backend.ManageSuccess_backend.exceptions.MSException;
import com.managesuccess_backend.ManageSuccess_backend.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    // Create a new task
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) throws MSException {
        TaskDTO createdTask = tasksService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get a task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable String taskId) throws MSException {
        TaskDTO taskDTO = tasksService.getTaskById(taskId);
        if (taskDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = tasksService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Update a task by ID with flexibility for partial updates
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String taskId,
                                              @RequestBody(required = false) TaskDTO taskDetails,
                                              @RequestParam(required = false) String taskName,
                                              @RequestParam(required = false) String description,
                                              @RequestParam(required = false) Status status,
                                              @RequestParam(required = false) Priority priority,
                                              @RequestParam(required = false) LocalDateTime startDate,
                                              @RequestParam(required = false) LocalDateTime dueDate,
                                              @RequestParam(required = false) String projectId,
                                              @RequestParam(required = false) String assignedToId) throws MSException {

        // If no request body is provided, initialize a new TaskDTO and populate it with query parameters
        if (taskDetails == null) {
            taskDetails = new TaskDTO();
            taskDetails.setTaskName(taskName);
            taskDetails.setDescription(description);
            taskDetails.setStatus(status);
            taskDetails.setPriority(priority);
            taskDetails.setStartDate(startDate);
            taskDetails.setDueDate(dueDate);
            taskDetails.setProjectId(projectId);
            taskDetails.setAssignedToId(assignedToId);
        }

        // Call the service to update the task
        TaskDTO updatedTask = tasksService.updateTask(taskId, taskDetails);

        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a task by ID
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) throws MSException {
        boolean isDeleted = tasksService.deleteTask(taskId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
