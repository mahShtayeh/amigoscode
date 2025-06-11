package com.amigoscode.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public <M> void publish(M payload, String exchange, String routingKey) {
        log.info("Publishing to: {}, using Routing Key: {}. Payload: {}", exchange, routingKey, payload);
        rabbitTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to: {}, using Routing Key: {}. Payload: {}", exchange, routingKey, payload);
    }
}