package com.repository;

import com.model.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JPA queries for the BadgeEntity table.
 */
@Repository
public interface BadgeRepository extends JpaRepository<BadgeEntity, Long> {

    @Query("select a from BadgeEntity a where a.badgeStatus = 'Active' and a.badgeExpiryDate > :now")
    List<BadgeEntity> getActiveBadges(LocalDateTime now);

    List<BadgeEntity> findBadgeByBadgeNumber(Long badgeNumber);

}

