package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CommentDTO;
import com.managesuccess_backend.ManageSuccess_backend.dtos.UserDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Comment;
import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import com.managesuccess_backend.ManageSuccess_backend.mappers.CommentMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.CommentsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private GlobalObjectsService globalObjectsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommentMapper commentMapper;

    // Create a new comment

    @Transactional
    public CommentDTO createComment(CommentDTO comment) {
        // Find the user creating the comment
        UserDTO userById = usersService.getUserById(comment.getCommentatorId());
        if (userById == null) {
            throw new EntityNotFoundException("User not found for id: " + comment.getCommentatorId());
        }else comment.setCommentator(userById);

        // Check if the object reference id exists; use that global object if so
        GlobalObjects globalObjects = globalObjectsService.getGlobalObjectByObjectReferenceId(comment.getObjectReference());
        if (globalObjects == null) {
            throw new EntityNotFoundException("GlobalObject not found for reference: " + comment.getObjectReference());
        }else comment.setGlobalObject(globalObjects); // Possibly only set the ID or reference

        Comment savedComment = commentsRepository.save(commentMapper.toCommentEntity(comment));

        // Create a new GlobalObject reference for this comment, if applicable
        globalObjectsService.createGlobalObjectFromEntity(savedComment);

        return commentMapper.toCommentDTO(savedComment);
    }

    // Get a comment by ID
    public CommentDTO getCommentById(String commentId) {
        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found for id: " + commentId));
        return commentMapper.toCommentDTO(comment);
    }

    // Get all comments
    public List<CommentDTO> getAllComments() {
        return commentsRepository.findAll().stream().map(commentMapper::toCommentDTO).collect(Collectors.toList());
    }

    // Update a comment by ID
    public CommentDTO updateComment(String commentId, String commentContent) {
        if (commentContent == null || commentContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty");
        }

        Comment commentToUpdate = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found for id: " + commentId));

        commentToUpdate.setContent(commentContent);
        return commentMapper.toCommentDTO(commentsRepository.save(commentToUpdate));
    }


    // Delete a comment by ID
    @Transactional
    public void deleteComment(String commentId) {
        Comment comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found for id: " + commentId));

        commentsRepository.delete(comment);
        globalObjectsService.deleteGlobalObjectByReferenceId(commentId);
    }

}
