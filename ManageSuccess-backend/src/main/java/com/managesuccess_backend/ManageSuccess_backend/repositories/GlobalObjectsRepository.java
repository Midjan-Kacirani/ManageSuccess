package com.managesuccess_backend.ManageSuccess_backend.repositories;

import com.managesuccess_backend.ManageSuccess_backend.entity.GlobalObjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalObjectsRepository extends JpaRepository<GlobalObjects, String> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM GlobalObjects WHERE objectReferenceId = :referenceId)", nativeQuery = true)
    boolean existsByReferenceId(@Param("referenceId") String referenceId);

    @Query(value = "SELECT * FROM GlobalObjects WHERE objectReferenceId = :referenceId", nativeQuery = true)
    GlobalObjects findByReferenceId(@Param("referenceId") String referenceId);
}

