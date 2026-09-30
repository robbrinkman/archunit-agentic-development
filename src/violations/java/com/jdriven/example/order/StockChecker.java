package com.jdriven.example.order;

import org.springframework.web.client.RestClient;

// Violates rule 4: calls another system directly instead of using a client in the client package
public class StockChecker {

    private final RestClient restClient = RestClient.create("https://inventory.example.com");

    public Integer stockFor(String productId) {
        return restClient.get().uri("/stock/{productId}", productId).retrieve().body(Integer.class);
    }
}
