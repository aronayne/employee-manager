package com.controller;

import com.model.DepartmentEntity;
import com.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Manages service REST requests for departments
 */
@RestController
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * Return all system departments
     *
     * @return all system departments
     */
    @GetMapping("/department")
    public List<DepartmentEntity> getDepartments() {

        logger.info("Finding all system departments");

        return departmentService.getDepartments();

    }

}
