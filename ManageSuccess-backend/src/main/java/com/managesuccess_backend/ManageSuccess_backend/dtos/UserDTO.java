package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiences;
import com.managesuccess_backend.ManageSuccess_backend.enums.UserRole;
import lombok.Data;
import java.util.*;

@Data
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private UserRole userRole;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private byte[] profilePictureBinaryData;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String companyId;

    @JsonIgnoreProperties(value = {"description", "type", "employeesNo", "website", "companyOwner"})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompanyDTO company;

}
