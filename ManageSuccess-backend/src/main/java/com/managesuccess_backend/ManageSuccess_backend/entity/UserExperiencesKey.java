package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UserExperiencesKey implements Serializable {

    private String userId;
    private String userExperiencesOptionsId;

}

