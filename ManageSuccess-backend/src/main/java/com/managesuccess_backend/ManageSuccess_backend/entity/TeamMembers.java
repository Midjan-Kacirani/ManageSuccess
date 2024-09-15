package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    private Teams team;

}

