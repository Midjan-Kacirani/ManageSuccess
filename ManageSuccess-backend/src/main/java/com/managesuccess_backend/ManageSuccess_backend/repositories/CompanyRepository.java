package com.managesuccess_backend.ManageSuccess_backend.repositories;

import com.managesuccess_backend.ManageSuccess_backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
}
