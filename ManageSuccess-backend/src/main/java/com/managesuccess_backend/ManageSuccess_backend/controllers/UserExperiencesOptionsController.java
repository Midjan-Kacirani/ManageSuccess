package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiencesOptions;
import com.managesuccess_backend.ManageSuccess_backend.services.UserExperiencesOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/user-experiences-options")
public class UserExperiencesOptionsController {

    @Autowired
    private UserExperiencesOptionsService userExperiencesOptionsService;

    // Create a new UserExperiencesOption
    @PostMapping
    public ResponseEntity<UserExperiencesOptions> createUserExperienceOption(@RequestBody UserExperiencesOptions userExperiencesOptions) {
        UserExperiencesOptions createdOption = userExperiencesOptionsService.createUserExperienceOption(userExperiencesOptions);
        return new ResponseEntity<>(createdOption, HttpStatus.CREATED);
    }

    // Get a UserExperiencesOption by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserExperiencesOptions> getUserExperienceOptionById(@PathVariable String id) {
        Optional<UserExperiencesOptions> option = userExperiencesOptionsService.getUserExperienceOptionById(id);
        return option.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all UserExperiencesOptions
    @GetMapping
    public ResponseEntity<List<UserExperiencesOptions>> getAllUserExperienceOptions() {
        List<UserExperiencesOptions> options = userExperiencesOptionsService.getAllUserExperienceOptions();
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    // Update a UserExperiencesOption by ID
    @PutMapping("/{id}")
    public ResponseEntity<UserExperiencesOptions> updateUserExperienceOption(@PathVariable String id, @RequestBody UserExperiencesOptions userExperiencesOptions) {
        UserExperiencesOptions updatedOption = userExperiencesOptionsService.updateUserExperienceOption(id, userExperiencesOptions);
        if (updatedOption != null) {
            return new ResponseEntity<>(updatedOption, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a UserExperiencesOption by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserExperienceOption(@PathVariable String id) {
        boolean deleted = userExperiencesOptionsService.deleteUserExperienceOption(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}