package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.generators.CustomIdWithPrefixGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import java.util.*;

@Entity(name = "companies")
@Data
public class Company extends BaseEntity{

    @Id
    @GeneratedValue(generator = "custom_id")
    @GenericGenerator(
            name = "custom_id",
            type = CustomIdWithPrefixGenerator.class,
            parameters = {@Parameter(name = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_PARAM, value = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_COMPANY)})
    private String companyId;

    private String name;
    private String description;
    private String type;
    private int employeesNo;
    private String website;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;
}
