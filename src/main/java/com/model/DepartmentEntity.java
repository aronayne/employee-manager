package com.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Table mapping to Department
 */
@Entity
@Table(name = "Department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Department_Code", nullable = false)
    private Long departmentCode;

    @Column(name = "Department_Name", nullable = false)
    private String departmentName;

    @JsonProperty("department_code")
    public Long getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer Integer) {
        this.departmentCode = departmentCode;
    }

    @JsonProperty("department_name")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


}
