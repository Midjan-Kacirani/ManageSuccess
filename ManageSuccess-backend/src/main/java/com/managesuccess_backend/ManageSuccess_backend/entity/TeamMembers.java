package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMembers {

    @EmbeddedId
    private TeamMembersKey id;

}

