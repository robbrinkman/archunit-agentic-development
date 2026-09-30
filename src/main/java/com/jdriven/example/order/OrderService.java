package com.jdriven.example.order;

import java.time.Clock;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final Clock clock;
    private final Map<String, OrderEntity> orders = new HashMap<>();

    public OrderService(Clock clock) {
        this.clock = clock;
    }

    public OrderEntity complete(String orderId) {
        OrderEntity order = orders.computeIfAbsent(orderId, OrderEntity::new);
        order.complete(Instant.now(clock));
        return order;
    }

    public List<OrderEntity> findAll() {
        return List.copyOf(orders.values());
    }
}
