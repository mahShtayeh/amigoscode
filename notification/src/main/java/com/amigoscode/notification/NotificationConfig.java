package com.amigoscode.notification;

import com.amigoscode.amqp.NotificationMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NotificationConfig {
    private final NotificationMQProperties notificationMQProperties;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(notificationMQProperties.getInternalExchange());
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationMQProperties.getNotificationQueue());
    }

    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(notificationMQProperties.getNotificationRoutingKey());
    }
}