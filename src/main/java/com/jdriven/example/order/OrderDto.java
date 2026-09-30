package com.jdriven.example.order;

import java.time.Instant;

public record OrderDto(String id, Instant completedAt) {

    static OrderDto from(OrderEntity order) {
        return new OrderDto(order.getId(), order.getCompletedAt());
    }
}
