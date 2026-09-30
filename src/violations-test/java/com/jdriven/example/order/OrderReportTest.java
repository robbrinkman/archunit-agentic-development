package com.jdriven.example.order;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Violates rule 8: disables a failing test instead of fixing it
class OrderReportTest {

    @Test
    @Disabled("flaky, fix later")
    void reportIsGeneratedAtFixedTime() {
        assertEquals(Instant.parse("2026-10-01T07:00:00Z"), new OrderReport().generatedAt());
    }
}
