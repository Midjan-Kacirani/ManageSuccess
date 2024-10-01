package com.managesuccess_backend.ManageSuccess_backend.repositories;

import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiences;
import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiencesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserExperiencesRepository extends JpaRepository<UserExperiences, UserExperiencesKey> {

    // Correcting the user parameter binding in the native query
    @Query(value = "SELECT * FROM User_Experiences WHERE user_id = :user", nativeQuery = true)
    List<UserExperiences> findByUserId(@Param("user") String user);

    // Correcting the experience parameter binding in the native query
    @Query(value = "SELECT * FROM User_Experiences WHERE user_experiences_options_id = :experience", nativeQuery = true)
    List<UserExperiences> findByExperienceId(@Param("experience") String experience);
}

