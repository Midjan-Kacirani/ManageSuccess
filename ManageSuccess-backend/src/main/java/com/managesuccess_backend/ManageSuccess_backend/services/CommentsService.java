package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.Comments;
import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import com.managesuccess_backend.ManageSuccess_backend.repositories.CommentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private GlobalObjectsService globalObjectsService;

    // Create a new comment

    @Transactional
    public Comments createComment(Comments comment) {
        comment.setCreatedAt(LocalDateTime.now());
        Comments savedComment = commentsRepository.save(comment);
        globalObjectsService.createGlobalObjectFromEntity(savedComment);
        return savedComment;
    }

    // Get a comment by ID
    public Optional<Comments> getCommentById(String commentId) {
        return commentsRepository.findById(commentId);
    }

    // Get all comments
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    // Update a comment by ID
    public Comments updateComment(String commentId, Comments commentDetails) {
        Optional<Comments> existingComment = commentsRepository.findById(commentId);
        if (existingComment.isPresent()) {
            Comments commentToUpdate = existingComment.get();
            commentToUpdate.setContent(commentDetails.getContent());
            commentToUpdate.setUpdatedAt(LocalDateTime.now());
            commentToUpdate.setGlobalObject(commentDetails.getGlobalObject());
            commentToUpdate.setUser(commentDetails.getUser());
            return commentsRepository.save(commentToUpdate);
        } else {
            return null;  // Comment not found
        }
    }

    // Delete a comment by ID
    @Transactional

    public boolean deleteComment(String commentId) {
        if (commentsRepository.existsById(commentId)) {
            commentsRepository.deleteById(commentId);
            globalObjectsService.deleteGlobalObjectByReferenceId(commentId);
            return true;
        }
        return false;
    }
}
