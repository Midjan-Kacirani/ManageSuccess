package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Users;
import com.managesuccess_backend.ManageSuccess_backend.mappers.UserMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // Create a new user
    public UserDTO createUser(Users user) {
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    // Get a user by ID
    public UserDTO getUserById(String userId) {
        Optional<Users> user = userRepository.findById(userId);
        return user.map(userMapper::toDTO).orElse(null);  // Returns null if not found
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    // Update a user
    public UserDTO updateUser(String userId, Users userUpdated) {
        Optional<Users> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            Users userToUpdate = existingUser.get();
            userToUpdate.setFirstName(userUpdated.getFirstName());
            userToUpdate.setLastName(userUpdated.getLastName());
            userToUpdate.setUsername(userUpdated.getUsername());
            userToUpdate.setEmail(userUpdated.getEmail());
            userToUpdate.setPassword(userUpdated.getPassword());
            userToUpdate.setProfilePictureBinaryData(userUpdated.getProfilePictureBinaryData());
            // Update other fields as necessary

            userRepository.save(userToUpdate);
            return userMapper.toDTO(userToUpdate);
        } else {
            return null;  // User not found
        }
    }

    // Delete a user by ID
    public boolean deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;  // User not found
    }
}
