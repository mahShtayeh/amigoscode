package com.amigoscode.fraud;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
    public boolean isFraudulentCustomer(final Long customerId) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .fraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false;
    }
}