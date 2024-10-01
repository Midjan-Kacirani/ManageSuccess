package com.managesuccess_backend.ManageSuccess_backend.exceptions;

public class MSException extends Exception{
    public static final String RESOURCE_NOT_FOUND = "Couldn't find the resource: {0}";

    public MSException(String message) {
        super(message);
    }
}
