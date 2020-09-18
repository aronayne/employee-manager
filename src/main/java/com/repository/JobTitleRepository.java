package com.repository;

import com.model.JobTitleEntity;
import com.model.JobTitleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA queries for the JobTitleEntity table.
 */
@Repository
public interface JobTitleRepository extends JpaRepository<JobTitleEntity, Long> {

    static final String BASE_NATIVE_QUERY = "SELECT jtitle.JOB_TITLE_CODE AS JOBTITLECODE, jtitle.JOB_TITLE_NAME " +
            " AS JOBTITLENAME, dept.DEPARTMENT_NAME AS " +
            "  DEPARTMENTNAME FROM JOB_TITLE jtitle," +
            "  DEPARTMENT dept where jtitle.department_code = dept.department_code";

    @Query(value = BASE_NATIVE_QUERY,
            nativeQuery = true)
    List<JobTitleMapping> findAllJobTitles();

    @Query(value = BASE_NATIVE_QUERY + " and " +
            " dept.DEPARTMENT_NAME = :departmentName",
            nativeQuery = true)
    List<JobTitleMapping> findJobTitleByDepartmentName(String departmentName);


}

