package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CommentDTO;
import com.managesuccess_backend.ManageSuccess_backend.services.CommentsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    // Create a new comment
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        CommentDTO createdComment = commentsService.createComment(commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Get a comment by ID
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable String commentId) {
        try {
            CommentDTO commentDTO = commentsService.getCommentById(commentId);
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentsService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Update a comment by ID
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String commentId, @RequestParam String commentContent) {
        try {
            CommentDTO updatedComment = commentsService.updateComment(commentId, commentContent);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a comment by ID
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        try {
            commentsService.deleteComment(commentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
