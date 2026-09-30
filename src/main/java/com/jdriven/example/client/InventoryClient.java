package com.jdriven.example.client;

import org.springframework.web.client.RestClient;

public class InventoryClient {

    private final RestClient restClient;

    public InventoryClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public int stockFor(String productId) {
        Integer stock = restClient.get()
            .uri("/stock/{productId}", productId)
            .retrieve()
            .body(Integer.class);
        return stock == null ? 0 : stock;
    }
}
