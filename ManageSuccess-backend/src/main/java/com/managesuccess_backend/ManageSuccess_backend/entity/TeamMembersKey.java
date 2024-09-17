package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class TeamMembersKey implements Serializable {

    private String userId;
    private String teamId;

}

