package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.Attachments;
import com.managesuccess_backend.ManageSuccess_backend.repositories.AttachmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentsService {

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    // Create a new attachment
    public Attachments createAttachment(Attachments attachment) {
        attachment.setCreatedAt(LocalDateTime.now());
        return attachmentsRepository.save(attachment);
    }

    // Get an attachment by ID
    public Optional<Attachments> getAttachmentById(String attachmentId) {
        return attachmentsRepository.findById(attachmentId);
    }

    // Get all attachments
    public List<Attachments> getAllAttachments() {
        return attachmentsRepository.findAll();
    }

    // Update an attachment by ID
    public Attachments updateAttachment(String attachmentId, Attachments attachmentDetails) {
        Optional<Attachments> existingAttachment = attachmentsRepository.findById(attachmentId);
        if (existingAttachment.isPresent()) {
            Attachments attachmentToUpdate = existingAttachment.get();
            attachmentToUpdate.setContent(attachmentDetails.getContent());
            attachmentToUpdate.setContentType(attachmentDetails.getContentType());
            attachmentToUpdate.setGlobalObject(attachmentDetails.getGlobalObject());
            return attachmentsRepository.save(attachmentToUpdate);
        } else {
            return null;  // Attachment not found
        }
    }

    // Delete an attachment by ID
    public boolean deleteAttachment(String attachmentId) {
        if (attachmentsRepository.existsById(attachmentId)) {
            attachmentsRepository.deleteById(attachmentId);
            return true;
        }
        return false;
    }
}
