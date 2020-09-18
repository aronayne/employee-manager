package com.repository;

import com.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * JPA queries for the DepartmentEntity table.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    @Query(value = "select DISTINCT(dept.departmentName) from DepartmentEntity dept")
    Set<String> findDepartmentNames();

}

