package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String commentId;
    private String content;
    private String objectReference;
    @JsonIgnore
    private GlobalObjects globalObject;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String commentatorId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDTO commentator;
}
