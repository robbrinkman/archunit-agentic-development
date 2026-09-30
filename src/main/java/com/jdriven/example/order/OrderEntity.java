package com.jdriven.example.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class OrderEntity {

    @Id
    private String id;
    private Instant completedAt;

    protected OrderEntity() {
    }

    public OrderEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void complete(Instant completedAt) {
        this.completedAt = completedAt;
    }
}
