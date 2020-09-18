package com.service;

import com.controller.BadgeController;
import com.model.EmployeeDetails;
import com.model.EmployeeDetailsMapping;
import com.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the employee services.
 */
@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(BadgeController.class);
    private EmployeeRepository employeeRepository;
    private CountryCodeService countryCodeService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CountryCodeService countryCodeService) {
        this.employeeRepository = employeeRepository;
        this.countryCodeService = countryCodeService;
    }

    /**
     * Map the country name for a given code.
     *
     * @param employeeDetailEntities
     * @return the country name for a given code.
     */
    private List<EmployeeDetails> mapEmployees(List<EmployeeDetailsMapping> employeeDetailEntities) {

        final List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();

        employeeDetailEntities.stream().forEach(employeeDetail -> {
            String countryForCode = countryCodeService.getCountry(employeeDetail.getCountryCode());
            final EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setId(employeeDetail.getId());
            employeeDetails.setFirstName(employeeDetail.getFirstName());
            employeeDetails.setLastName(employeeDetail.getLastName());
            employeeDetails.setBadgeNumber(employeeDetail.getBadgeNumber());
            employeeDetails.setCountry(countryForCode);
            employeeDetails.setJobTitle(employeeDetail.getJobTitleName());
            employeeDetails.setDepartment(employeeDetail.getDepartment());
            employeeDetails.setStartDate(employeeDetail.getStartDate());
            employeeDetails.setLeaveDate(employeeDetail.getLeaveDate());
            employeeDetailsList.add(employeeDetails);
        });

        return employeeDetailsList;

    }

    /**
     * Get all employees.
     *
     * @return all employees.
     */
    public List<EmployeeDetails> getEmployees() {

        logger.info("Retrieving all employees");

        final List<EmployeeDetailsMapping> employeeDetailEntities = employeeRepository.findEmployees();
        return mapEmployees(employeeDetailEntities);
    }

    /**
     * Get all active employees.
     *
     * @return all active employees.
     */
    public List<EmployeeDetails> getActiveEmployees() {

        logger.info("Retrieving all active employees");

        final LocalDateTime now = LocalDateTime.now();
        final List<EmployeeDetailsMapping> employeeDetailEntities = employeeRepository.getActiveEmployees(now);

        return mapEmployees(employeeDetailEntities);
    }

    /**
     * Get all employees for a given department.
     *
     * @param departmentName
     * @return All employees for a given department.
     */
    public List<EmployeeDetails> getEmployeesByDepartment(String departmentName) {

        logger.info("Retrieving all employees by department");

        final List<EmployeeDetailsMapping> employeeDetailEntities = employeeRepository.getEmployeesByDepartment(departmentName);

        return mapEmployees(employeeDetailEntities);
    }

}
