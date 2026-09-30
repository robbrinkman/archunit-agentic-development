package com.jdriven.example.order;

import java.util.Date;

// Violates rule 2: uses java.util.Date instead of java.time
public class LegacyOrderExport {

    public Date exportedAt() {
        return new Date();
    }
}
