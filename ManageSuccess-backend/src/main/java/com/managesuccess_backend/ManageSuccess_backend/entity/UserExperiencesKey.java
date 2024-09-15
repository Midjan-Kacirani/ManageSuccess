package com.managesuccess_backend.ManageSuccess_backend.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserExperiencesKey implements Serializable {

    private Long userId;
    private Long userExperiencesOptionsId;

    // getters, setters, equals, and hashCode
}

