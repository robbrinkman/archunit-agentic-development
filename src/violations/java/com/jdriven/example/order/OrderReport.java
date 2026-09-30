package com.jdriven.example.order;

import java.time.Instant;

// Violates rule 3: reads the system clock instead of using the injected Clock
public class OrderReport {

    public Instant generatedAt() {
        return Instant.now();
    }
}
