package com.amigoscode.clients.fraud;

import lombok.Builder;

@Builder
public record NotificationRequest(
        Long toCustomerId,
        String toCustomerEmail,
        String message
) {
}