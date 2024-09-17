package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.UserExperiencesOptions;
import com.managesuccess_backend.ManageSuccess_backend.repositories.UserExperiencesOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserExperiencesOptionsService {

    @Autowired
    private UserExperiencesOptionsRepository userExperiencesOptionsRepository;

    // Create a new UserExperiencesOption
    public UserExperiencesOptions createUserExperienceOption(UserExperiencesOptions userExperiencesOptions) {
        return userExperiencesOptionsRepository.save(userExperiencesOptions);
    }

    // Get a UserExperiencesOption by ID
    public Optional<UserExperiencesOptions> getUserExperienceOptionById(String id) {
        return userExperiencesOptionsRepository.findById(id);
    }

    // Get all UserExperiencesOptions
    public List<UserExperiencesOptions> getAllUserExperienceOptions() {
        return userExperiencesOptionsRepository.findAll();
    }

    // Update a UserExperiencesOption
    public UserExperiencesOptions updateUserExperienceOption(String id, UserExperiencesOptions userExperiencesOptions) {
        Optional<UserExperiencesOptions> existingOption = userExperiencesOptionsRepository.findById(id);
        if (existingOption.isPresent()) {
            UserExperiencesOptions updatedOption = existingOption.get();
            updatedOption.setExperienceName(userExperiencesOptions.getExperienceName());
            return userExperiencesOptionsRepository.save(updatedOption);
        } else {
            return null;  // Option not found
        }
    }

    // Delete a UserExperiencesOption by ID
    public boolean deleteUserExperienceOption(String id) {
        if (userExperiencesOptionsRepository.existsById(id)) {
            userExperiencesOptionsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

