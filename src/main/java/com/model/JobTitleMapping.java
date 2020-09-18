package com.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Maps result of queries in JobTitleRepository
 */
public interface JobTitleMapping {

    @JsonProperty("job_title_code")
    Integer getJobTitleCode();

    @JsonProperty("job_title_name")
    String getJobTitleName();

    @JsonProperty("department_name")
    String getDepartmentName();

}
