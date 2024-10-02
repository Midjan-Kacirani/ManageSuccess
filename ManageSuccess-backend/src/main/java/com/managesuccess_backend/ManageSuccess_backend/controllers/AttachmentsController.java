package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.AttachmentDTO;
import com.managesuccess_backend.ManageSuccess_backend.services.AttachmentsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentsController {

    @Autowired
    private AttachmentsService attachmentsService;

    // Create a new attachment
    @PostMapping
    public ResponseEntity<AttachmentDTO> createAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        try {
            AttachmentDTO createdAttachment = attachmentsService.createAttachment(attachmentDTO);
            return new ResponseEntity<>(createdAttachment, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // GlobalObject not found
        }
    }

    // Get an attachment by ID
    @GetMapping("/{attachmentId}")
    public ResponseEntity<AttachmentDTO> getAttachmentById(@PathVariable String attachmentId) {
        try {
            AttachmentDTO attachmentDTO = attachmentsService.getAttachmentById(attachmentId);
            return new ResponseEntity<>(attachmentDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all attachments
    @GetMapping
    public ResponseEntity<List<AttachmentDTO>> getAllAttachments() {
        List<AttachmentDTO> attachments = attachmentsService.getAllAttachments();
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    // Update an attachment by ID
    @PutMapping("/{attachmentId}")
    public ResponseEntity<AttachmentDTO> updateAttachment(@PathVariable String attachmentId, @RequestBody AttachmentDTO attachmentDetails) {
        try {
            AttachmentDTO updatedAttachment = attachmentsService.updateAttachment(attachmentId, attachmentDetails);
            return new ResponseEntity<>(updatedAttachment, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an attachment by ID
    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable String attachmentId) {
        try {
            attachmentsService.deleteAttachment(attachmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
