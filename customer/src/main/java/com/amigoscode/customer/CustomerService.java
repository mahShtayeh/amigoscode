package com.amigoscode.customer;


import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.fraud.NotificationClient;
import com.amigoscode.clients.fraud.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        NotificationClient notificationClient,
        FraudClient fraudClient
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
        FraudCheckResponse fraudCheckResponse = fraudClient.checkFraud(customer.getId());

        Assert.notNull(fraudCheckResponse, "Invalid fraud check response");
        Assert.isTrue(!fraudCheckResponse.isFraudster(), "Fraudster customer");

        notificationClient.sendNotification(NotificationRequest.builder()
                        .toCustomerId(customer.getId())
                        .toCustomerEmail(customer.getEmail())
                        .message(String.format("New Customer created with ID: %d", customer.getId()))
                .build());
    }
}