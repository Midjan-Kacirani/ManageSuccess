package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.generators.CustomIdWithPrefixGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Data
public class Teams {

    @Id
    @GeneratedValue(generator = "custom_id")
    @GenericGenerator(
            name = "custom_id",
            type = CustomIdWithPrefixGenerator.class,
            parameters = {@Parameter(name = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_PARAM, value = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_TEAM)})
    private String teamId;

    private String teamName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "teamLeadId", nullable = false)
    private User teamLead;

}

