package com.jdriven.example.order;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {

    private static final Instant NOW = Instant.parse("2026-10-01T07:00:00Z");

    private final OrderService orderService = new OrderService(Clock.fixed(NOW, ZoneOffset.UTC));

    @Test
    void completingAnOrderUsesTheInjectedClock() {
        OrderEntity order = orderService.complete("order-1");

        assertEquals(NOW, order.getCompletedAt());
    }
}
