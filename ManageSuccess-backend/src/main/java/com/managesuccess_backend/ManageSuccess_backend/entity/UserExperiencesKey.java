package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExperiencesKey implements Serializable {

    private String userId;
    private String userExperiencesOptionsId;

}

