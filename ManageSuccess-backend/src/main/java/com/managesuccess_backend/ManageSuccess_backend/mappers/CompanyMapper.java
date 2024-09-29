package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO toDTO(Company company) {
        if (company == null) return null;
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setName(company.getName());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setType(company.getType());
        companyDTO.setEmployeesNo(company.getEmployeesNo());
        companyDTO.setWebsite(company.getWebsite());
        return companyDTO;
    }

    public Company toEntity(CompanyDTO companyDTO) {
        if (companyDTO == null) return null;
        Company company = new Company();
        company.setCompanyId(companyDTO.getCompanyId());
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setType(companyDTO.getType());
        company.setEmployeesNo(companyDTO.getEmployeesNo());
        company.setWebsite(companyDTO.getWebsite());
        return company;
    }
}
