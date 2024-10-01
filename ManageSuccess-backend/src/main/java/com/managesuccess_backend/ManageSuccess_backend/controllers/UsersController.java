package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.enums.UserRole;
import com.managesuccess_backend.ManageSuccess_backend.exceptions.MSException;
import com.managesuccess_backend.ManageSuccess_backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = null;
        try {
            createdUser = userService.createUser(userDTO);
        } catch (MSException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        UserDTO userDTO = userService.getUserById(userId);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Update a user by ID
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId,
                                              @RequestBody(required = false) UserDTO userUpdated,
                                              @RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName,
                                              @RequestParam(required = false) String username,
                                              @RequestParam(required = false) UserRole userRole,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) String password,
                                              @RequestParam(required = false) byte[] profilePictureBinaryData,
                                              @RequestParam(required = false) String companyId) throws MSException {
        if(userUpdated == null){
            userUpdated = new UserDTO();
            userUpdated.setFirstName(firstName);
            userUpdated.setLastName(lastName);
            userUpdated.setUsername(username);
            userUpdated.setUserRole(userRole);
            userUpdated.setEmail(email);
            userUpdated.setPassword(password);
            userUpdated.setProfilePictureBinaryData(profilePictureBinaryData);
            userUpdated.setCompanyId(companyId);
        }
        UserDTO updatedUser = userService.updateUser(userId, userUpdated);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
