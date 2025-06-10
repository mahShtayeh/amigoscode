package com.amigoscode.clients.fraud;

public record NotificationRequest(
        Long toCustomerId,
        String toCustomerEmail,
        String message
) {
}