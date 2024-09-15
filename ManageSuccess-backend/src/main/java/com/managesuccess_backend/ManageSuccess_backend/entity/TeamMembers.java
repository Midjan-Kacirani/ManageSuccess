package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.*;

@Entity
public class TeamMembers {

    @EmbeddedId
    private TeamMembersKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "teamId")
    private Team team;

    // getters and setters
}

