package com.managesuccess_backend.ManageSuccess_backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyDTO {

    private String companyId;
    private String name;
    private String description;
    private String type;
    private int employeesNo;
    private String website;

}

