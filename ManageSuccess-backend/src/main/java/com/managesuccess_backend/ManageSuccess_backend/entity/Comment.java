package com.managesuccess_backend.ManageSuccess_backend.entity;

import com.managesuccess_backend.ManageSuccess_backend.generators.CustomIdWithPrefixGenerator;
import com.managesuccess_backend.ManageSuccess_backend.utils.GlobalObjectsInterface;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity(name = "comments")
@Data
public class Comment extends BaseEntity implements GlobalObjectsInterface {

    @Id
    @GeneratedValue(generator = "custom_id")
    @GenericGenerator(
            name = "custom_id",
            type = CustomIdWithPrefixGenerator.class,
            parameters = {@Parameter(name = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_PARAM, value = CustomIdWithPrefixGenerator.CUSTOM_ID_PREFIX_COMMENT)})
    private String commentId;

    private String content;

    @ManyToOne
    @JoinColumn(name = "objectId", nullable = false)
    private GlobalObjects globalObject;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Override
    public String getPrimaryKey() {
        return this.getCommentId();
    }
}

