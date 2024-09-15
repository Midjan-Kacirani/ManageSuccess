package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.enums.ExperienceLevel;
import jakarta.persistence.*;

@Entity
public class UserExperiences {

    @EmbeddedId
    private UserExperiencesKey id;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @MapsId("userExperiencesOptionsId")
    @JoinColumn(name = "userExperiencesOptionsId")
    private UserExperiencesOptions userExperiencesOptions;

    // getters and setters
}

