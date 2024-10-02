package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.generators.CustomIdWithPrefixGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity(name = "attachments")
@Data
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue(generator = "custom_id")
    @GenericGenerator(
            name = "custom_id",
            type = CustomIdWithPrefixGenerator.class,
            parameters = {@Parameter(name = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_PARAM, value = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_ATTACHMENT)})
    private String attachmentId;

    @Lob
    private byte[] content;

    private String contentType;

    @ManyToOne
    @JoinColumn(name = "objectId", nullable = false)
    private GlobalObjects globalObject;

}

