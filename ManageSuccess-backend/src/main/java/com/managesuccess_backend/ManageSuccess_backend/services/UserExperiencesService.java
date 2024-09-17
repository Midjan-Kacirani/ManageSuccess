package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Companies;
import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiences;
import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiencesKey;
import com.managesuccess_backend.ManageSuccess_backend.enums.ExperienceLevel;
import com.managesuccess_backend.ManageSuccess_backend.repositories.UserExperiencesRepository;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserExperiencesService {

    @Autowired
    private UserExperiencesRepository userExperiencesRepository;

    // Create a new UserExperiences
    public UserExperiences createUserExperience(UserExperiences userExperiences) {
        return userExperiencesRepository.save(userExperiences);
    }

    //Get a userExperience
    public UserExperiences getUserExperiencesById(String user, String experience) {
        if (Utilities.isNullOrEmpty(user) || Utilities.isNullOrEmpty(experience)) return null;
        else {
            Optional<UserExperiences> byId = userExperiencesRepository.findById(new UserExperiencesKey(user, experience));
            return byId.orElse(null);
        }
    }

    public List<UserExperiences> getUserExperiencesByUserIdOrByExperienceId(String user, String experience){
        if(!Utilities.isNullOrEmpty(user)) return userExperiencesRepository.findByUserId(user);
        else if (!Utilities.isNullOrEmpty(experience)) return userExperiencesRepository.findByExperienceId(experience);
        return null;
    }

    // Update an userExperience
    public UserExperiences updateUserExperience(String userId, String experience, String experiences) {
        UserExperiences userExperiences = this.getUserExperiencesById(userId, experience);
        if(userExperiences != null){
            userExperiences.setExperienceLevel(ExperienceLevel.valueOf(experiences));
            return userExperiencesRepository.save(userExperiences);
        } else {
            return null;  // Record not found
        }
    }

    // Delete an userExperience by ID
    public boolean deleteUserExperience(String userId, String experienceId) {
        UserExperiencesKey userExperiencesKey = new UserExperiencesKey(userId, experienceId);
        if (userExperiencesRepository.existsById(userExperiencesKey)) {
            userExperiencesRepository.deleteById(userExperiencesKey);
            return true;
        }
        return false;
    }

}
