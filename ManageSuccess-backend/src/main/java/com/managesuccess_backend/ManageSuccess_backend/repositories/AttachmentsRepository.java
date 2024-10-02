package com.managesuccess_backend.ManageSuccess_backend.repositories;


import com.managesuccess_backend.ManageSuccess_backend.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachment, String> {
}
