package com.amigoscode.apigw.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.AssertTrue;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiKeyAuthorizationFilter implements GlobalFilter, Ordered {
    private final FakeApiAuthorizationChecker fakeApiAuthorizationChecker;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("API Key Authorization Filter... Checking the key");

        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        Assert.notNull(route, "Something went wrong");
        String application = route.getId();

        Assert.isTrue(exchange.getRequest().getHeaders().containsKey("ApiKey"), "No Key Found");
        String apiKey = exchange.getRequest().getHeaders().get("ApiKey").stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "you are not Authorized"));

        if (application == null || !fakeApiAuthorizationChecker.isAuthorized(apiKey, application)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "you are not Authorized");
        }

        log.info("API Key: {}", apiKey);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}