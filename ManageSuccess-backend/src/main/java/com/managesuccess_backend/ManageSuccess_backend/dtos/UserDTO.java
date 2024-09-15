package com.managesuccess_backend.ManageSuccess_backend.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private byte[] profilePictureBinaryData;
    private String companyName;
}
