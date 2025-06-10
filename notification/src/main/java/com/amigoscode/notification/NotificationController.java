package com.amigoscode.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponse sendNotification(@RequestBody final NotificationRequest notificationRequest) {
        final Long notificationId = notificationService.sendNotification(notificationRequest);

        return NotificationResponse.builder()
                .notificationId(notificationId)
                .build();
    }
}