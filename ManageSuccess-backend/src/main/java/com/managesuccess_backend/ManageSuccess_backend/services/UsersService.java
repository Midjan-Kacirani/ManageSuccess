package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.User;
import com.managesuccess_backend.ManageSuccess_backend.exceptions.MSException;
import com.managesuccess_backend.ManageSuccess_backend.mappers.CompanyMapper;
import com.managesuccess_backend.ManageSuccess_backend.mappers.UserMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.UsersRepository;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    // Create a new user
    public UserDTO createUser(UserDTO user) throws MSException {
        CompanyDTO companyById = companyService.getCompanyById(user.getCompanyId());
        if(companyById == null) throw new MSException(MSException.RESOURCE_NOT_FOUND + " Company");
        //Create the user
        User userEntity = userMapper.toEntity(user);
        userEntity.setCompany(companyMapper.toEntity(companyById));
        //Save the user
        return userMapper.toDTO(userRepository.save(userEntity));
    }

    // Get a user by ID
    public UserDTO getUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(userMapper::toDTO).orElse(null);  // Returns null if not found
    }

    // Get all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    // Update a user
    public UserDTO updateUser(String userId, UserDTO userUpdated) throws MSException {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            CompanyDTO companyById = null;
            if(!Utilities.isNullOrEmpty(userUpdated.getCompanyId())) companyById = companyService.getCompanyById(userUpdated.getCompanyId());
            User userToUpdate = existingUser.get();
            if(!Utilities.isNullOrEmpty(userUpdated.getFirstName())) userToUpdate.setFirstName(userUpdated.getFirstName());
            if(!Utilities.isNullOrEmpty(userUpdated.getLastName())) userToUpdate.setLastName(userUpdated.getLastName());
            if(!Utilities.isNullOrEmpty(userUpdated.getUsername())) userToUpdate.setUsername(userUpdated.getUsername());
            if(!Utilities.isNullOrEmpty(String.valueOf(userUpdated.getUserRole()))) userToUpdate.setUserRole(userUpdated.getUserRole());
            if(!Utilities.isNullOrEmpty(userUpdated.getEmail())) userToUpdate.setEmail(userUpdated.getEmail());
            if(!Utilities.isNullOrEmpty(userUpdated.getPassword())) userToUpdate.setPassword(userUpdated.getPassword());
            if(!Utilities.isNullOrEmpty(Arrays.toString(userUpdated.getProfilePictureBinaryData()))) userToUpdate.setProfilePictureBinaryData(userUpdated.getProfilePictureBinaryData());
            if(companyById != null) userToUpdate.setCompany(companyMapper.toEntity(companyById));

            return userMapper.toDTO(userRepository.save(userToUpdate));
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
