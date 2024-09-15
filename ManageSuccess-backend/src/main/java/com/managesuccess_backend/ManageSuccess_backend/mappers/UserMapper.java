package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Users;


public class UserMapper {

    // Convert from User entity to UserDTO
    public UserDTO toDTO(Users user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setProfilePictureBinaryData(user.getProfilePictureBinaryData());
        userDTO.setCompanyName(user.getCompany() != null ? user.getCompany().getName() : null);

        return userDTO;
    }

//    // Convert from UserDTO to User entity
//    public Users toEntity(UserDTO userDTO) {
//        if (userDTO == null) {
//            return null;
//        }
//
//        Users user = new Users();
//        user.setUserId(userDTO.getUserId());
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setUsername(userDTO.getUsername());
//        user.setEmail(userDTO.getEmail());
//        user.setProfilePictureBinaryData(userDTO.getProfilePictureBinaryData());
//        // Assuming the company is set elsewhere, as this involves more complex logic
//
//        return user;
//    }
}
