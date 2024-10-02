package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.managesuccess_backend.ManageSuccess_backend.enums.Priority;
import com.managesuccess_backend.ManageSuccess_backend.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String taskId;

    private String taskName;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dueDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String assignedToId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDTO assignedTo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String projectId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProjectDTO project;

}
