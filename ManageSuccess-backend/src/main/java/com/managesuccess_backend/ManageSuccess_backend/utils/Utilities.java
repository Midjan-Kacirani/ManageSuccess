package com.managesuccess_backend.ManageSuccess_backend.utils;

import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiences;

import java.util.Optional;

public class Utilities {
    public static boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty() || value.isBlank();
    }

    public static String STATUS_OPERATION_SUCCEDDED = "OPERATION_SUCCEEDED";
    public static String STATUS_OPERATION_FAILED = "OPERATION_FAILED";
    public static String MESSAGE_SUCCESSFULLY_DELETED = "The record is successfully deleted!";
    public static String MESSAGE_COULDNT_BE_FOUND = "The record couldn't be found!";
}
