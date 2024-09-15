package com.managesuccess_backend.ManageSuccess_backend.repositories;

import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiences;
import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiencesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExperiencesRepository extends JpaRepository<UserExperiences, UserExperiencesKey> {
}
