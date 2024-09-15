package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TeamMembersKey implements Serializable {

    private Long userId;
    private Long teamId;

    // getters, setters, equals, and hashCode
}

