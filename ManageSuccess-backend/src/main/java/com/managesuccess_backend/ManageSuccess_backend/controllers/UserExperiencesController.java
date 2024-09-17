package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiences;
import com.managesuccess_backend.ManageSuccess_backend.services.UserExperiencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-experiences")
public class UserExperiencesController {

    @Autowired
    private UserExperiencesService userExperiencesService;

    // Create a new UserExperience
    @PostMapping
    public ResponseEntity<UserExperiences> createUserExperience(@RequestBody UserExperiences userExperiences) {
        UserExperiences createdExperience = userExperiencesService.createUserExperience(userExperiences);
        return new ResponseEntity<>(createdExperience, HttpStatus.CREATED);
    }

    // Get a UserExperience by composite key (userId and experienceId)
    @GetMapping("/{userId}/{experienceId}")
    public ResponseEntity<UserExperiences> getUserExperienceById(
            @PathVariable String userId,
            @PathVariable String experienceId) {
        UserExperiences userExperience = userExperiencesService.getUserExperiencesById(userId, experienceId);
        if (userExperience != null) {
            return new ResponseEntity<>(userExperience, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get UserExperiences by userId or experienceId
    @GetMapping
    public ResponseEntity<List<UserExperiences>> getUserExperiencesByUserIdOrExperienceId(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String experienceId) {
        List<UserExperiences> experiences = userExperiencesService.getUserExperiencesByUserIdOrByExperienceId(userId, experienceId);
        if (experiences != null && !experiences.isEmpty()) {
            return new ResponseEntity<>(experiences, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing UserExperience
    @PutMapping("/{userId}/{experienceId}")
    public ResponseEntity<UserExperiences> updateUserExperience(
            @PathVariable String userId,
            @PathVariable String experienceId,
            @RequestBody String updatedExperience) {
        UserExperiences updatedUserExperience = userExperiencesService.updateUserExperience(userId, experienceId, updatedExperience);
        if (updatedUserExperience != null) {
            return new ResponseEntity<>(updatedUserExperience, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a UserExperience by composite key (userId and experienceId)
    @DeleteMapping("/{userId}/{experienceId}")
    public ResponseEntity<Void> deleteUserExperience(
            @PathVariable String userId,
            @PathVariable String experienceId) {
        boolean isDeleted = userExperiencesService.deleteUserExperience(userId, experienceId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

