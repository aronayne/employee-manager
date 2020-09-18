package com.controller;

import com.model.BadgeEntity;
import com.service.BadgeService;
import com.validation.BadgeNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Manages service REST requests for badges
 */
@RestController
public class BadgeController {

    private static final Logger logger = LoggerFactory.getLogger(BadgeController.class);
    private final BadgeService badgeService;
    private final BadgeNumberValidator badgeNumberValidator;

    @Autowired
    public BadgeController(BadgeService badgeService, BadgeNumberValidator badgeNumberValidator, BadgeNumberValidator badgeNumberValidator1) {
        this.badgeNumberValidator = badgeNumberValidator1;
        this.badgeService = badgeService;
    }

    /**
     * Return all system badges or return the badge matching the badge_number query param
     *
     * @param requestParameters
     * @return 404 if no badge_number matches, 422 if query param is not correct or the passed value is non numeric
     */
    @GetMapping("/badges")
    public ResponseEntity<List<BadgeEntity>> getBadges(@RequestParam final Map<String, String> requestParameters) {

        if (requestParameters.isEmpty()) {
            return new ResponseEntity<>(badgeService.getBadges(), HttpStatus.OK);
        }

        final String badgeNumber = requestParameters.get(RequestParameterNames.BADGE_NUMBER.parameterName);

        logger.info("Finding badges with badge number :{} ", badgeNumber);

        if (!requestParameters.containsKey(RequestParameterNames.BADGE_NUMBER.parameterName) || !this.badgeNumberValidator.isNumeric(badgeNumber)) {
            logger.info("Validation of URL parameter badge_number failed");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        final List<BadgeEntity> badgeEntityList = badgeService.getBadgesByNumber(Long.valueOf(badgeNumber));
        if (badgeEntityList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(badgeEntityList, HttpStatus.OK);
        }
    }

    /**
     * Return only badges whose badge_status is ‘Active’ and whose badge_expiry_date is not in the past
     *
     * @return 404 if no badges are active,
     */
    @GetMapping("/badges/active")
    public ResponseEntity<List<BadgeEntity>> getActiveBadges() {

        logger.info("Finding active badges");

        List<BadgeEntity> activeBadges = badgeService.getActiveBadges();
        if (activeBadges.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activeBadges, HttpStatus.OK);
        }
    }

}
