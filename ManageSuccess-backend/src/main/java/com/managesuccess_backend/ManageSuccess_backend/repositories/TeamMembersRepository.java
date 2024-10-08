package com.managesuccess_backend.ManageSuccess_backend.repositories;

import com.managesuccess_backend.ManageSuccess_backend.entity.TeamMembers;
import com.managesuccess_backend.ManageSuccess_backend.entity.TeamMembersKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMembersRepository extends JpaRepository<TeamMembers, TeamMembersKey> {
}
