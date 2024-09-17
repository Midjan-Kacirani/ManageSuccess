package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.Attachments;
import com.managesuccess_backend.ManageSuccess_backend.services.AttachmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentsController {

    @Autowired
    private AttachmentsService attachmentsService;

    // Create a new attachment
    @PostMapping
    public ResponseEntity<Attachments> createAttachment(@RequestBody Attachments attachment) {
        Attachments createdAttachment = attachmentsService.createAttachment(attachment);
        return new ResponseEntity<>(createdAttachment, HttpStatus.CREATED);
    }

    // Get an attachment by ID
    @GetMapping("/{attachmentId}")
    public ResponseEntity<Attachments> getAttachmentById(@PathVariable String attachmentId) {
        Optional<Attachments> attachment = attachmentsService.getAttachmentById(attachmentId);
        return attachment.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all attachments
    @GetMapping
    public ResponseEntity<List<Attachments>> getAllAttachments() {
        List<Attachments> attachments = attachmentsService.getAllAttachments();
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    // Update an attachment by ID
    @PutMapping("/{attachmentId}")
    public ResponseEntity<Attachments> updateAttachment(@PathVariable String attachmentId, @RequestBody Attachments attachmentDetails) {
        Attachments updatedAttachment = attachmentsService.updateAttachment(attachmentId, attachmentDetails);
        if (updatedAttachment != null) {
            return new ResponseEntity<>(updatedAttachment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an attachment by ID
    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable String attachmentId) {
        boolean isDeleted = attachmentsService.deleteAttachment(attachmentId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
