package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import com.managesuccess_backend.ManageSuccess_backend.repositories.GlobalObjectsRepository;
import com.managesuccess_backend.ManageSuccess_backend.utils.GlobalObjectsInterface;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlobalObjectsService {

    @Autowired
    private GlobalObjectsRepository globalObjectsRepository;

    // Create a new GlobalObject
    public GlobalObjects createGlobalObject(GlobalObjects globalObject) {
        return globalObjectsRepository.save(globalObject);
    }

    public GlobalObjects createGlobalObjectFromEntity(GlobalObjectsInterface globalObjectsInterface) {
        GlobalObjects globalObjectRecord = new GlobalObjects();
        globalObjectRecord.setObjectReferenceId(globalObjectsInterface.getPrimaryKey());
        return globalObjectsRepository.save(globalObjectRecord);
    }

    // Get a GlobalObject by ID
    public Optional<GlobalObjects> getGlobalObjectById(String globalObjectId) {
        return globalObjectsRepository.findById(globalObjectId);
    }

    // Get all GlobalObjects
    public List<GlobalObjects> getAllGlobalObjects() {
        return globalObjectsRepository.findAll();
    }

    // Update a GlobalObject by ID
    public GlobalObjects updateGlobalObject(String globalObjectId, GlobalObjects globalObjectDetails) {
        Optional<GlobalObjects> existingGlobalObject = globalObjectsRepository.findById(globalObjectId);
        if (existingGlobalObject.isPresent()) {
            GlobalObjects globalObjectToUpdate = existingGlobalObject.get();
            globalObjectToUpdate.setObjectReferenceId(globalObjectDetails.getObjectReferenceId());
            return globalObjectsRepository.save(globalObjectToUpdate);
        } else {
            return null;  // GlobalObject not found
        }
    }

    // Delete a GlobalObject by ID
    public boolean deleteGlobalObject(String globalObjectId) {
        if (globalObjectsRepository.existsById(globalObjectId)) {
            globalObjectsRepository.deleteById(globalObjectId);
            return true;
        }
        return false;
    }

    public boolean deleteGlobalObjectByReferenceId(String referenceObjectId){
        if(globalObjectsRepository.existsByReferenceId(referenceObjectId) == 1){
            globalObjectsRepository.delete(globalObjectsRepository.findByReferenceId(referenceObjectId));
            return true;
        } return false;
    }

    public GlobalObjects getGlobalObjectByObjectReferenceId(String objectReference) {
        return globalObjectsRepository.findByReferenceId(objectReference);
    }
}
