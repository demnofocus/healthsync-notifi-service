package com.healthsync.notifi_service.service;

import com.healthsync.notifi_service.entity.Notification;
import com.healthsync.notifi_service.repository.NotificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public NotificationService(NotificationRepository notificationRepository, JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    // Method to create a new notification
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Method to send email notifications
    @Scheduled(fixedRate = 60000) // Runs every minute
    public void sendScheduledNotifications() {
        List<Notification> notifications = notificationRepository.findByScheduledTimeBeforeAndSentFalse(LocalDateTime.now());

        for (Notification notification : notifications) {
            try {
                // Create and send email
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(notification.getEmail());
                message.setSubject("Appointment Reminder");
                message.setText(notification.getMessage());
                mailSender.send(message);

                // Mark notification as sent
                notification.setSent(true);
                notificationRepository.save(notification);
            } catch (Exception e) {
                System.err.println("Failed to send notification: " + e.getMessage());
            }
        }
    }
}