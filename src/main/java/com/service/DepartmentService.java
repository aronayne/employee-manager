package com.service;

import com.model.DepartmentEntity;
import com.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Manages the department services.
 */
@Service
public class DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Get all department
     *
     * @return all departments
     */
    public List<DepartmentEntity> getDepartments() {

        logger.info("Retrieving all departments");

        return departmentRepository.findAll();
    }

    /**
     * Get all department names
     *
     * @return all department names
     */
    public Set<String> getDepartmentNames() {

        logger.info("Retrieving all departments");

        return departmentRepository.findDepartmentNames();
    }

}
