package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Company;
import com.managesuccess_backend.ManageSuccess_backend.mappers.CompanyMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.CompanyRepository;
import com.managesuccess_backend.ManageSuccess_backend.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    // Create a new company
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDTO(company);
    }

    // Get a company by ID
    public CompanyDTO getCompanyById(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        return company.map(companyMapper::toDTO).orElse(null);
    }

    // Get all companies
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::toDTO).collect(Collectors.toList());
    }

    // Update a company
    public CompanyDTO updateCompany(String companyId, CompanyDTO companyDTO) {
        Optional<Company> existingCompany = companyRepository.findById(companyId);

        if (existingCompany.isPresent()) {

            Company companyToUpdate = existingCompany.get();
            if(!Utilities.isNullOrEmpty(companyDTO.getName())) companyToUpdate.setName(companyDTO.getName());
            if(!Utilities.isNullOrEmpty(companyDTO.getDescription())) companyToUpdate.setDescription(companyDTO.getDescription());
            if(!Utilities.isNullOrEmpty(companyDTO.getType())) companyToUpdate.setType(companyDTO.getType());
            if(companyDTO.getEmployeesNo() != -1) companyToUpdate.setEmployeesNo(companyDTO.getEmployeesNo());
            if(!Utilities.isNullOrEmpty(companyDTO.getWebsite()))companyToUpdate.setWebsite(companyDTO.getWebsite());

            companyRepository.save(companyToUpdate);
            return companyMapper.toDTO(companyToUpdate);
        } else {
            return null;  // Company not found
        }
    }

    // Delete a company by ID
    public boolean deleteCompany(String companyId) {
        if (companyRepository.existsById(companyId)) {
            companyRepository.deleteById(companyId);
            return true;
        }
        return false;
    }
}
