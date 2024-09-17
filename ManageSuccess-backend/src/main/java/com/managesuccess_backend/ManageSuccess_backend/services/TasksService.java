package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.Tasks;
import com.managesuccess_backend.ManageSuccess_backend.repositories.TasksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private GlobalObjectsService globalObjectsService;

    // Create a new task

    @Transactional
    public Tasks createTask(Tasks task) {
        task.setCreatedAt(LocalDateTime.now());
        Tasks savedTask = tasksRepository.save(task);
        globalObjectsService.createGlobalObjectFromEntity(savedTask);
        return savedTask;
    }

    // Get a task by ID
    public Optional<Tasks> getTaskById(String taskId) {
        return tasksRepository.findById(taskId);
    }

    // Get all tasks
    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    // Update a task by ID
    public Tasks updateTask(String taskId, Tasks taskDetails) {
        Optional<Tasks> existingTask = tasksRepository.findById(taskId);
        if (existingTask.isPresent()) {
            Tasks taskToUpdate = existingTask.get();
            taskToUpdate.setTaskName(taskDetails.getTaskName());
            taskToUpdate.setDescription(taskDetails.getDescription());
            taskToUpdate.setStatus(taskDetails.getStatus());
            taskToUpdate.setPriority(taskDetails.getPriority());
            taskToUpdate.setStartDate(taskDetails.getStartDate());
            taskToUpdate.setEndDate(taskDetails.getEndDate());
            taskToUpdate.setDueDate(taskDetails.getDueDate());
            taskToUpdate.setAssignedTo(taskDetails.getAssignedTo());
            taskToUpdate.setProject(taskDetails.getProject());
            return tasksRepository.save(taskToUpdate);
        } else {
            return null;  // Task not found
        }
    }

    // Delete a task by ID

    @Transactional
    public boolean deleteTask(String taskId) {
        if (tasksRepository.existsById(taskId)) {
            tasksRepository.deleteById(taskId);
            globalObjectsService.deleteGlobalObjectByReferenceId(taskId);
            return true;
        }
        return false;
    }
}
