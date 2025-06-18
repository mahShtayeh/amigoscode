package com.amigoscode.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud", url = "${clients.fraud.url}", path = "api/v1/frauds")
public interface FraudClient {
    @GetMapping("{customerId}/check")
    FraudCheckResponse checkFraud(@PathVariable(name = "customerId") Long customerId);
}