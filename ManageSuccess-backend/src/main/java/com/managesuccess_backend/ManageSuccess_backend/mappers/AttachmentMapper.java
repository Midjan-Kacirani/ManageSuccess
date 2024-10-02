package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.AttachmentDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Attachment;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

    public AttachmentDTO toAttachmentDTO(Attachment attachment) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setAttachmentId(attachment.getAttachmentId());
        attachmentDTO.setContentType(attachment.getContentType());
        attachmentDTO.setContent(attachment.getContent());
        attachmentDTO.setObjectReference(attachment.getGlobalObject().getObjectReferenceId());
        return attachmentDTO;
    }

    public Attachment toAttachmentEntity(AttachmentDTO attachmentDTO) {
        Attachment attachment = new Attachment();
        if (!Utilities.isNullOrEmpty(attachmentDTO.getAttachmentId())) {
            attachment.setAttachmentId(attachmentDTO.getAttachmentId());
        }
        attachment.setContentType(attachmentDTO.getContentType());
        attachment.setContent(attachmentDTO.getContent());
        attachment.setGlobalObject(attachmentDTO.getGlobalObject());
        return attachment;
    }
}
