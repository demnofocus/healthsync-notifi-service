package com.healthsync.notifi_service.repository;

import com.healthsync.notifi_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByScheduledTimeBeforeAndSentFalse(LocalDateTime now);
}
