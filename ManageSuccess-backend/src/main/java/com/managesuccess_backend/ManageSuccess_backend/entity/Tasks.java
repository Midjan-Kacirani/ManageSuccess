package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.enums.Priority;
import com.managesuccess_backend.ManageSuccess_backend.enums.Status;
import com.managesuccess_backend.ManageSuccess_backend.generators.CustomIdWithPrefixGenerator;
import com.managesuccess_backend.ManageSuccess_backend.utils.GlobalObjectsInterface;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity
@Data
public class Tasks extends BaseEntity implements GlobalObjectsInterface {

    @Id
    @GeneratedValue(generator = "custom_id")
    @GenericGenerator(
            name = "custom_id",
            type = CustomIdWithPrefixGenerator.class,
            parameters = {@Parameter(name = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_PARAM, value = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_TASK)})
    private String taskId;

    private String taskName;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "assignedTo", nullable = false)
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    private Project project;

    @Override
    public String getPrimaryKey() {
        return this.getTaskId();
    }
}

