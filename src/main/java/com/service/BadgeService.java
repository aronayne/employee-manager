package com.service;

import com.controller.BadgeController;
import com.model.BadgeEntity;
import com.repository.BadgeRepository;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;
import com.repository.JobTitleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Manages the badge services.
 */
@Service
public class BadgeService {

    private static final Logger logger = LoggerFactory.getLogger(BadgeController.class);
    private BadgeRepository badgeRepository;

    @Autowired
    public BadgeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                        BadgeRepository badgeRepository, JobTitleRepository jobTitleRepository) {
        this.badgeRepository = badgeRepository;
    }

    /**
     * Get all badges
     *
     * @return all badges
     */
    public List<BadgeEntity> getBadges() {
        return badgeRepository.findAll();
    }

    /**
     * Get all badges by badgeNumber
     *
     * @param badgeNumber
     * @return all badges by badgeNumber
     */
    public List<BadgeEntity> getBadgesByNumber(Long badgeNumber) {
        return badgeRepository.findBadgeByBadgeNumber(badgeNumber);
    }

    /**
     * Get all active badges
     *
     * @return all active badges
     */
    public List<BadgeEntity> getActiveBadges() {

        LocalDateTime now = LocalDateTime.now();
        logger.info("now is :{}", now);
        return badgeRepository.getActiveBadges(LocalDateTime.now());
    }

}
