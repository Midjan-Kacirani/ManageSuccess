package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.enums.ExperienceLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExperiences {

    @EmbeddedId
    private UserExperiencesKey id;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

}

