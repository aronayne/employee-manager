package com.model;

import java.time.LocalDateTime;

/**
 * Maps result of queries in EmployeeRepository
 */
public interface EmployeeDetailsMapping {

    Long getId();

    String getFirstName();

    String getLastName();

    String getBadgeNumber();

    String getCountryCode();

    String getJobTitleName();

    String getDepartment();

    LocalDateTime getStartDate();

    LocalDateTime getLeaveDate();
}
