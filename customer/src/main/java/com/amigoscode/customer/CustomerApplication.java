package com.amigoscode.customer;

import com.amigoscode.clients.fraud.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
        "com.amigoscode.customer",
        "com.amigoscode.amqp"
})
@PropertySource(
        value = "classpath:clients-${spring.profiles.active}.yml",
        factory = YamlPropertySourceFactory.class
)
@EnableFeignClients(basePackages = "com.amigoscode.clients")
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}