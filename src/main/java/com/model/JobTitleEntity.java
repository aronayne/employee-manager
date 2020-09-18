package com.model;


import javax.persistence.*;

@Entity
public class JobTitleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOBTITLECODE")
    private Long jobTitleCode;

    @Column(name = "JOBTITLENAME")
    private String jobTitleName;

    @Column(name = "DEPARTMENTNAME")
    private String departmentName;

    public String getJobTitleName() {
        return jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getJobTitleCode() {
        return jobTitleCode;
    }

    public void setJobTitleCode(Long jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }
}
