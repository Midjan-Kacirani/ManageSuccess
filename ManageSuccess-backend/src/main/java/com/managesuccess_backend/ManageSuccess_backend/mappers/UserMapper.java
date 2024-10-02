package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    @Autowired
    private CompanyMapper companyMapper;

    // Convert from User entity to UserDTO
    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setUserRole(user.getUserRole());
        userDTO.setProfilePictureBinaryData(user.getProfilePictureBinaryData());
        userDTO.setCompany(companyMapper.toDTO(user.getCompany()));

        return userDTO;
    }

    // Convert from UserDTO to User entity
    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        if(userDTO.getUserId() != null) user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setUserRole(userDTO.getUserRole());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setProfilePictureBinaryData(userDTO.getProfilePictureBinaryData());
        return user;
    }
}
