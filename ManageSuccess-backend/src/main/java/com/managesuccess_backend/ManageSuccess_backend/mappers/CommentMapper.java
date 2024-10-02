package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CommentDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Comment;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    @Autowired
    private UserMapper userMapper;
    public CommentDTO toCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setCommentator(userMapper.toDTO(comment.getUser()));
        commentDTO.setObjectReference(comment.getGlobalObject().getObjectReferenceId());
        return commentDTO;
    }

    public Comment toCommentEntity(CommentDTO commentDTO){
        Comment comment = new Comment();
        if(!Utilities.isNullOrEmpty(commentDTO.getCommentId())) comment.setCommentId(commentDTO.getCommentId());
        comment.setContent(commentDTO.getContent());
        comment.setGlobalObject(commentDTO.getGlobalObject());
        comment.setUser(userMapper.toEntity(commentDTO.getCommentator()));
        return comment;
    }
}
