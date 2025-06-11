package com.amigoscode.notification.rabbitmq;

import com.amigoscode.clients.fraud.NotificationRequest;
import com.amigoscode.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void cosumer(NotificationRequest notificationRequest) {
        log.info("Consumed Message: {} from Queue", notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}