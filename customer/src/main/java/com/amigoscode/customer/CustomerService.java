package com.amigoscode.customer;


import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate
) {
    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        // todo: check if email is valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForEntity(
                "http://FRAUD/api/v1/frauds/{customerId}/check",
                FraudCheckResponse.class,
                customer.getId()
        ).getBody();

        Assert.notNull(fraudCheckResponse, "Invalid fraud check response");
        Assert.isTrue(!fraudCheckResponse.isFraudster(), "Fraudster customer");
        // todo: Send notifications
    }
}