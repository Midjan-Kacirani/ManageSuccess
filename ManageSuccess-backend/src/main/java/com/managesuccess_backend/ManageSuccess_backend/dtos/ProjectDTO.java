package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.managesuccess_backend.ManageSuccess_backend.enums.Status;
import com.managesuccess_backend.ManageSuccess_backend.mappers.TeamMapper;
import com.managesuccess_backend.ManageSuccess_backend.mappers.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String projectId;

    private String projectName;
    private String description;
    private Status status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String teamId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TeamDTO team;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String createdById;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDTO createdBy;
}
