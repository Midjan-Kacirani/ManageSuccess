package com.managesuccess_backend.ManageSuccess_backend.services;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Companies;
import com.managesuccess_backend.ManageSuccess_backend.mappers.CompanyMapper;
import com.managesuccess_backend.ManageSuccess_backend.repositories.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompaniesRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    // Create a new company
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Companies company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDTO(company);
    }

    // Get a company by ID
    public CompanyDTO getCompanyById(String companyId) {
        Optional<Companies> company = companyRepository.findById(companyId);
        return company.map(companyMapper::toDTO).orElse(null);
    }

    // Get all companies
    public List<CompanyDTO> getAllCompanies() {
        List<Companies> companies = companyRepository.findAll();
        return companies.stream().map(companyMapper::toDTO).collect(Collectors.toList());
    }

    // Update a company
    public CompanyDTO updateCompany(String companyId, CompanyDTO companyDTO) {
        Optional<Companies> existingCompany = companyRepository.findById(companyId);

        if (existingCompany.isPresent()) {
            Companies companyToUpdate = existingCompany.get();
            companyToUpdate.setName(companyDTO.getName());
            companyToUpdate.setDescription(companyDTO.getDescription());
            companyToUpdate.setType(companyDTO.getType());
            companyToUpdate.setEmployeesNo(companyDTO.getEmployeesNo());
            companyToUpdate.setWebsite(companyDTO.getWebsite());

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
