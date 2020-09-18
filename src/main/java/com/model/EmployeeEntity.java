package com.model;


import javax.persistence.*;
import java.time.LocalDate;

/**
 * Maps result of queries in EmployeeRepository
 */
@Table(name = "Employee")
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "Firstname", nullable = false)
    private String firstName;

    @Column(name = "Lastname", nullable = false)
    private String lastName;

    @Column(name = "badge_number", nullable = false)
    private String badgeNumber;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    @Column(name = "department", nullable = false)
    private String department;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "leave_date", nullable = true)
    private LocalDate leaveDate;

}
