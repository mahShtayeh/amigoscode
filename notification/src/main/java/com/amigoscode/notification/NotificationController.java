package com.amigoscode.notification;

import com.amigoscode.clients.fraud.NotificationRequest;
import com.amigoscode.clients.fraud.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse sendNotification(@RequestBody final NotificationRequest notificationRequest) {
        log.info("Notification sent... {}", notificationRequest);
        final Long notificationId = notificationService.sendNotification(notificationRequest);

        return NotificationResponse.builder()
                .notificationId(notificationId)
                .build();
    }
}