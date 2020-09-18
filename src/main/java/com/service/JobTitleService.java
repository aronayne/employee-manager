package com.service;

import com.model.JobTitleMapping;
import com.repository.JobTitleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleService {

    private static final Logger logger = LoggerFactory.getLogger(JobTitleService.class);
    private JobTitleRepository jobTitleRepository;

    @Autowired
    public JobTitleService(JobTitleRepository jobTitleRepository) {
        this.jobTitleRepository = jobTitleRepository;
    }

    /**
     * Get all job titles.
     *
     * @return all job titles.
     */
    public List<JobTitleMapping> getJobTitles() {
        logger.info("Finding job titles");
        return jobTitleRepository.findAllJobTitles();
    }

    /**
     * Get all job titles for a given department name
     *
     * @param departmentName
     * @return All job titles for a given department name
     */
    public List<JobTitleMapping> getJobTitleByDepartmentName(String departmentName) {
        logger.info("Accessing job titles by department name");
        return jobTitleRepository.findJobTitleByDepartmentName(departmentName);
    }

}
