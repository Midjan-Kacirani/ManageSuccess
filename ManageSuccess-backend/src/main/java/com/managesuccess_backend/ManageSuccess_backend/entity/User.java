package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.enums.UserRole;
import com.managesuccess_backend.ManageSuccess_backend.generators.CustomIdWithPrefixGenerator;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity(name = "users")
@Data
public class User extends BaseEntity{

    @Id
    @GeneratedValue(generator = "custom_id")
    @GenericGenerator(
            name = "custom_id",
            type = CustomIdWithPrefixGenerator.class,
            parameters = {@Parameter(name = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_PARAM, value = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_USER)})
    private String userId;
    private String firstName;
    private String lastName;
    private String username;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String email;
    private String password;

    @Lob
    private byte[] profilePictureBinaryData;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

}
