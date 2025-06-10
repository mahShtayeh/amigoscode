package com.amigoscode.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification", path = "api/v1/notifications")
public interface NotificationClient {
    @PostMapping
    NotificationResponse sendNotification(NotificationRequest notificationRequest);
}