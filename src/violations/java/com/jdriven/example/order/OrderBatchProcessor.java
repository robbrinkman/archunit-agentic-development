package com.jdriven.example.order;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Violates rule 7: introduces concurrency outside the async package
public class OrderBatchProcessor {

    public void completeAll(OrderService orderService, List<String> orderIds) {
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
            orderIds.forEach(orderId -> executor.submit(() -> orderService.complete(orderId)));
        }
    }
}
