package com.amigoscode.fraud;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/frauds")
public class FraudController {
    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId}/check")
    public FraudCheckResponse checkFraud(@PathVariable final Long customerId) {
        log.info("Fraud check request for customer: {}", customerId);
        final Boolean isFraudster = fraudCheckService.isFraudulentCustomer(customerId);

        return FraudCheckResponse.builder()
                .isFraudster(isFraudster)
                .build();
    }
}