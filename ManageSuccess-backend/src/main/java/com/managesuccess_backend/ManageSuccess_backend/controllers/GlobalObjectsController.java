package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import com.managesuccess_backend.ManageSuccess_backend.services.GlobalObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/global-objects")
public class GlobalObjectsController {

    @Autowired
    private GlobalObjectsService globalObjectsService;

    // Create a new GlobalObject
    @PostMapping
    public ResponseEntity<GlobalObjects> createGlobalObject(@RequestBody GlobalObjects globalObject) {
        GlobalObjects createdGlobalObject = globalObjectsService.createGlobalObject(globalObject);
        return new ResponseEntity<>(createdGlobalObject, HttpStatus.CREATED);
    }

    // Get a GlobalObject by ID
    @GetMapping("/{globalObjectId}")
    public ResponseEntity<GlobalObjects> getGlobalObjectById(@PathVariable String globalObjectId) {
        Optional<GlobalObjects> globalObject = globalObjectsService.getGlobalObjectById(globalObjectId);
        return globalObject.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all GlobalObjects
    @GetMapping
    public ResponseEntity<List<GlobalObjects>> getAllGlobalObjects() {
        List<GlobalObjects> globalObjects = globalObjectsService.getAllGlobalObjects();
        return new ResponseEntity<>(globalObjects, HttpStatus.OK);
    }

    // Update a GlobalObject by ID
    @PutMapping("/{globalObjectId}")
    public ResponseEntity<GlobalObjects> updateGlobalObject(@PathVariable String globalObjectId, @RequestBody GlobalObjects globalObjectDetails) {
        GlobalObjects updatedGlobalObject = globalObjectsService.updateGlobalObject(globalObjectId, globalObjectDetails);
        if (updatedGlobalObject != null) {
            return new ResponseEntity<>(updatedGlobalObject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a GlobalObject by ID
    @DeleteMapping("/{globalObjectId}")
    public ResponseEntity<Void> deleteGlobalObject(@PathVariable String globalObjectId) {
        boolean isDeleted = globalObjectsService.deleteGlobalObject(globalObjectId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
