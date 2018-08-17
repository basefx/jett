package com.dovgopolyy.jett.client.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

public class RestTemplateSupplier implements Supplier<RestTemplate> {
    @Value("${server.api.url}")
    private String apiUrl;

    @Override
    public RestTemplate get() {
        return new RestTemplateBuilder()
                .rootUri(apiUrl)
                .build();
    }
}
