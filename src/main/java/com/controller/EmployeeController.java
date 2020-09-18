package com.controller;

import com.model.EmployeeDetails;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Manages service REST requests for employees
 */
@RestController
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    /**
     * Return all system employees or Return all employees from a specific department
     *
     * @param requestParameters
     * @return All system employees
     * 404 if no employees are in that department
     * 422 if the department provided is not valid or the query param is incorrect
     */
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDetails>> getBadges(@RequestParam Map<String, String> requestParameters) {

        String requestParametersString = requestParameters.values().stream().map(Object::toString).collect(Collectors.joining("="));
        logger.info("Finding employees with parameters :{} ", requestParametersString);

        if (requestParameters.isEmpty()) {
            return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
        }

        if (!requestParameters.containsKey(RequestParameterNames.DEPARTMENT_NAME.parameterName)) {
            logger.info("Validation of URL parameter department_name failed");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final String departmentName = requestParameters.get(RequestParameterNames.DEPARTMENT_NAME.parameterName);
        final Set<String> departmentNames = departmentService.getDepartmentNames();
        if (!departmentNames.contains(departmentName)) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        logger.info("Finding employees with department name :{} ", departmentName);

        return new ResponseEntity<>(employeeService.getEmployeesByDepartment(departmentName), HttpStatus.OK);

    }

    /**
     * Return all employees who DO NOT have a leave_date set or have a leave_date set in the past
     *
     * @return 404 if there are no active employees
     */
    @GetMapping("/employees/active")
    public ResponseEntity<List<EmployeeDetails>> getActiveEmployees() {

        logger.info("Finding all active employees");

        List<EmployeeDetails> activeEmployees = employeeService.getActiveEmployees();

        if (activeEmployees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activeEmployees, HttpStatus.OK);
        }


    }


}
