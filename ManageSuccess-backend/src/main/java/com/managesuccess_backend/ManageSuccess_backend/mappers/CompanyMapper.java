package com.managesuccess_backend.ManageSuccess_backend.mappers;

import com.managesuccess_backend.ManageSuccess_backend.dtos.CompanyDTO;
import com.managesuccess_backend.ManageSuccess_backend.entity.Companies;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO toDTO(Companies company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyId(company.getCompanyId());
        companyDTO.setName(company.getName());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setType(company.getType());
        companyDTO.setEmployeesNo(company.getEmployeesNo());
        companyDTO.setWebsite(company.getWebsite());
        return companyDTO;
    }

    public Companies toEntity(CompanyDTO companyDTO) {
        Companies company = new Companies();
        company.setCompanyId(companyDTO.getCompanyId());
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setType(companyDTO.getType());
        company.setEmployeesNo(companyDTO.getEmployeesNo());
        company.setWebsite(companyDTO.getWebsite());
        return company;
    }
}
