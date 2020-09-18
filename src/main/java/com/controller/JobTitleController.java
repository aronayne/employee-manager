package com.controller;

import com.model.JobTitleMapping;
import com.service.DepartmentService;
import com.service.JobTitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Manages service REST requests for job titles
 */
@RestController
public class JobTitleController {

    private static final Logger logger = LoggerFactory.getLogger(JobTitleController.class);
    private final JobTitleService jobTitleService;
    private final DepartmentService departmentService;

    @Autowired
    public JobTitleController(JobTitleService jobTitleService, DepartmentService departmentService) {
        this.jobTitleService = jobTitleService;
        this.departmentService = departmentService;
    }

    /**
     * Return all system job_titles
     *
     * @return All system job_titles
     */
    @GetMapping("/job_titles")
    public List<JobTitleMapping> getJobTitles() {

        logger.info("Accessing all job titles");

        return jobTitleService.getJobTitles();
    }

    /**
     * Return all job titles from with the department_name URL param
     *
     * @param departmentName
     * @return 404 if no job_titles are in that department, 422 if the deparment_name passed is not a valid department
     */
    @GetMapping("/job_titles/{departmentName}")
    public ResponseEntity<List<JobTitleMapping>> getJobTitlesByDepartment(@PathVariable final String departmentName) {

        logger.info("Accessing all job titles for department :{}", departmentName);

        final Set<String> departmentNames = departmentService.getDepartmentNames();
        if (!departmentNames.contains(departmentName)) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final List<JobTitleMapping> jobTitleEntityList = jobTitleService.getJobTitleByDepartmentName(departmentName);
        if (jobTitleEntityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(jobTitleEntityList, HttpStatus.OK);
        }

    }

}
