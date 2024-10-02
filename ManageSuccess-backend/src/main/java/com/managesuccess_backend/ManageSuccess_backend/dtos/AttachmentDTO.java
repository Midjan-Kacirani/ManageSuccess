package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import lombok.Data;

import java.time.Instant;

@Data
public class AttachmentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String attachmentId;

    private byte[] content;  // Ignored in the response for security and efficiency

    private String contentType;

    private String objectReference;

    @JsonIgnore
    private GlobalObjects globalObject;
}
