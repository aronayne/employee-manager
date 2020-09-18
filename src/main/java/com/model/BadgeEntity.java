package com.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Table mapping to Badge
 */
@Entity
@Table(name = "Badge")
public class BadgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Badge_Number")
    private Long badgeNumber;

    @Column(name = "Badge_Status")
    private String badgeStatus;

    @Column(name = "Badge_Expiry_Date")
    private LocalDateTime badgeExpiryDate;

    @JsonProperty("badge_number")
    public Long getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(Long badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    @JsonProperty("badge_status")
    public String getBadgeStatus() {
        return badgeStatus;
    }

    public void setBadgeStatus(String badgeStatus) {
        this.badgeStatus = badgeStatus;
    }

    @JsonProperty("badge_expiry_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getBadgeExpiryDate() {
        return badgeExpiryDate;
    }

    public void setBadgeExpiryDate(LocalDateTime badgeExpiryDate) {
        this.badgeExpiryDate = badgeExpiryDate;
    }


}
