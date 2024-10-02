package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeamDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String teamId;
    private String teamName;
    private String teamDescription;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String teamLeadId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDTO teamLead;
}
