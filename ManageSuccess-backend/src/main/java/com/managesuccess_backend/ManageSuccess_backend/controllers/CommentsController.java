package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.Comments;
import com.managesuccess_backend.ManageSuccess_backend.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    // Create a new comment
    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
        Comments createdComment = commentsService.createComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // Get a comment by ID
    @GetMapping("/{commentId}")
    public ResponseEntity<Comments> getCommentById(@PathVariable String commentId) {
        Optional<Comments> comment = commentsService.getCommentById(commentId);
        return comment.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all comments
    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {
        List<Comments> comments = commentsService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Update a comment by ID
    @PutMapping("/{commentId}")
    public ResponseEntity<Comments> updateComment(@PathVariable String commentId, @RequestBody Comments commentDetails) {
        Comments updatedComment = commentsService.updateComment(commentId, commentDetails);
        if (updatedComment != null) {
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a comment by ID
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        boolean isDeleted = commentsService.deleteComment(commentId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
