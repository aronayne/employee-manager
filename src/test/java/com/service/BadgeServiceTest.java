package com.service;

import com.model.BadgeEntity;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Test the CountriesService methods.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BadgeServiceTest {

    @Autowired
    private BadgeService badgeService;

    /**
     * Test all badges returned are active and the badge expiry date is after the current date
     */
    @Test
    public void testActiveBadges() {

        final LocalDateTime currentDateTime = LocalDateTime.now();
        final List<BadgeEntity> badgeDetails = badgeService.getActiveBadges();

        badgeDetails.forEach(badgeDetail -> {
            Assertions.assertThat(badgeDetail.getBadgeStatus()).isEqualTo("Active");
            Assertions.assertThat(badgeDetail.getBadgeExpiryDate()).isAfter(currentDateTime);
        });

    }

}
