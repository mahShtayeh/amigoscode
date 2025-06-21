package com.amigoscode.apigw.security;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("fake")
public class FakeApiAuthorizationChecker implements ApiKeyAuthorizationChecker {
    private static final Map<String, List<String>> keys;

    static {
        keys = Map.of(
                "supersecure", List.of("customer")
        );
    }

    @Override
    public boolean isAuthorized(String key, String application) {
        return keys.getOrDefault(key, List.of()).stream()
                .anyMatch(apiKey -> apiKey.contains(application));
    }
}