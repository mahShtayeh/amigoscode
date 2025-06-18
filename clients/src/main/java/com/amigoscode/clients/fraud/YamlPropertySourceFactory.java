package com.amigoscode.clients.fraud;

import lombok.SneakyThrows;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.List;

public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    @SneakyThrows
    public PropertySource<?> createPropertySource(final String name, final EncodedResource resource){
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        List<PropertySource<?>> sources = loader.load(resource.getResource().getFilename(), resource.getResource());
        return sources.isEmpty() ? null : sources.get(0);
    }
}