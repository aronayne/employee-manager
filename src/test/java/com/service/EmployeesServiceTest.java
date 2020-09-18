package com.service;

import com.model.EmployeeDetails;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Test the CountriesService methods.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesServiceTest {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Test all non null leave dates occur in the past
     */
    @Test
    public void testActiveEmployeesLeaveDate() {

        final LocalDateTime currentDateTime = LocalDateTime.now();
        final List<EmployeeDetails> employeeDetails = employeeService.getActiveEmployees();


        final List<EmployeeDetails> notNullLeaveDates = employeeDetails.stream().filter(
                employeeDetail -> (employeeDetail.getLeaveDate() != null))
                .collect(Collectors.toList());

        //Verify all leave dates are set in the past
        notNullLeaveDates.forEach(employeeDetail -> {
            Assertions.assertThat(employeeDetail.getLeaveDate()).isBefore(currentDateTime);
        });


    }

}
