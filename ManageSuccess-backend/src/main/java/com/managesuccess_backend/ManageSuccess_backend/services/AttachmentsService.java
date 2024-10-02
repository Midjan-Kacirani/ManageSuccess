package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.AttachmentDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Attachment;
import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import com.managesuccess_backend.ManageSuccess_backend.mappers.AttachmentMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.AttachmentsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttachmentsService {

    @Autowired
    private AttachmentsRepository attachmentsRepository;

    @Autowired
    private GlobalObjectsService globalObjectsService;

    @Autowired
    private AttachmentMapper attachmentMapper;

    // Create a new attachment
    @Transactional
    public AttachmentDTO createAttachment(AttachmentDTO attachmentDTO) {
        // Check if the object reference id exists
        GlobalObjects globalObject = globalObjectsService.getGlobalObjectByObjectReferenceId(attachmentDTO.getObjectReference());
        if (globalObject == null) {
            throw new EntityNotFoundException("GlobalObject not found for reference: " + attachmentDTO.getObjectReference());
        } else {
            attachmentDTO.setGlobalObject(globalObject);
        }

        // Convert DTO to entity and save
        Attachment savedAttachment = attachmentsRepository.save(attachmentMapper.toAttachmentEntity(attachmentDTO));

        return attachmentMapper.toAttachmentDTO(savedAttachment);
    }

    // Get an attachment by ID
    public AttachmentDTO getAttachmentById(String attachmentId) {
        Attachment attachment = attachmentsRepository.findById(attachmentId)
                .orElseThrow(() -> new EntityNotFoundException("Attachment not found for id: " + attachmentId));
        return attachmentMapper.toAttachmentDTO(attachment);
    }

    // Get all attachments
    public List<AttachmentDTO> getAllAttachments() {
        return attachmentsRepository.findAll().stream()
                .map(attachmentMapper::toAttachmentDTO)
                .collect(Collectors.toList());
    }

    // Update an attachment by ID
    public AttachmentDTO updateAttachment(String attachmentId, AttachmentDTO attachmentDetails) {
        Attachment attachmentToUpdate = attachmentsRepository.findById(attachmentId)
                .orElseThrow(() -> new EntityNotFoundException("Attachment not found for id: " + attachmentId));

        attachmentToUpdate.setContent(attachmentDetails.getContent());
        attachmentToUpdate.setContentType(attachmentDetails.getContentType());

        return attachmentMapper.toAttachmentDTO(attachmentsRepository.save(attachmentToUpdate));
    }

    // Delete an attachment by ID
    @Transactional
    public boolean deleteAttachment(String attachmentId) {
        Attachment attachment = attachmentsRepository.findById(attachmentId)
                .orElseThrow(() -> new EntityNotFoundException("Attachment not found for id: " + attachmentId));

        attachmentsRepository.delete(attachment);
        return true;
    }
}
