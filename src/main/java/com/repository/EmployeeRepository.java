package com.repository;

import com.model.EmployeeDetailsMapping;
import com.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JPA queries for the EmployeeEntity table.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    static final String BASE_NATIVE_QUERY = "SELECT EMP.ID, EMP.FIRSTNAME, EMP.LASTNAME, EMP.BADGE_NUMBER AS BADGENUMBER, EMP.COUNTRY_CODE AS COUNTRYCODE," +
            " JTITLE.JOB_TITLE_NAME AS JOBTITLENAME,DEPT.DEPARTMENT_NAME AS DEPARTMENT,EMP.START_DATE AS STARTDATE,EMP.LEAVE_DATE AS LEAVEDATE" +
            " FROM EMPLOYEE EMP, JOB_TITLE JTITLE, DEPARTMENT DEPT" +
            " WHERE EMP.JOB_TITLE_CODE = JTITLE.JOB_TITLE_CODE" +
            " AND DEPT.DEPARTMENT_CODE = JTITLE.DEPARTMENT_CODE";

    @Query(value = BASE_NATIVE_QUERY,
            nativeQuery = true)
    List<EmployeeDetailsMapping> findEmployees();

    @Query(value = BASE_NATIVE_QUERY + " and (EMP.LEAVE_DATE is null or EMP.LEAVE_DATE < :now)",
            nativeQuery = true)
    List<EmployeeDetailsMapping> getActiveEmployees(LocalDateTime now);

    @Query(value = BASE_NATIVE_QUERY + " and DEPT.DEPARTMENT_NAME = :departmentName",
            nativeQuery = true)
    List<EmployeeDetailsMapping> getEmployeesByDepartment(String departmentName);


}
