package com.managesuccess_backend.ManageSuccess_backend.controllers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Create a new company
    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO createdCompany = companyService.createCompany(companyDTO);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    // Get a company by ID
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable String companyId) {
        CompanyDTO companyDTO = companyService.getCompanyById(companyId);
        if (companyDTO != null) {
            return new ResponseEntity<>(companyDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all companies
    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Update a company by ID
    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable String companyId, @RequestBody CompanyDTO companyDTO) {
        CompanyDTO updatedCompany = companyService.updateCompany(companyId, companyDTO);
        if (updatedCompany != null) {
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a company by ID
    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String companyId) {
        boolean isDeleted = companyService.deleteCompany(companyId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
