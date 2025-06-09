package com.amigoscode.customer;

import lombok.Builder;

@Builder
public record FraudCheckResponse(Boolean isFraudster) {
}