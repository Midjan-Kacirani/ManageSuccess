package com.managesuccess_backend.ManageSuccess_backend.repositories;

import com.managesuccess_backend.ManageSuccess_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {
}
