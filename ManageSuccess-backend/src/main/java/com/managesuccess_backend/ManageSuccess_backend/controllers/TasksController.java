package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.Tasks;
import com.managesuccess_backend.ManageSuccess_backend.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    // Create a new task
    @PostMapping
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks task) {
        Tasks createdTask = tasksService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Get a task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable String taskId) {
        Optional<Tasks> task = tasksService.getTaskById(taskId);
        return task.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<Tasks>> getAllTasks() {
        List<Tasks> tasks = tasksService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Update a task by ID
    @PutMapping("/{taskId}")
    public ResponseEntity<Tasks> updateTask(@PathVariable String taskId, @RequestBody Tasks taskDetails) {
        Tasks updatedTask = tasksService.updateTask(taskId, taskDetails);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a task by ID
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        boolean isDeleted = tasksService.deleteTask(taskId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
