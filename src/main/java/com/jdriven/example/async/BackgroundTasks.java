package com.jdriven.example.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundTasks implements AutoCloseable {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public CompletableFuture<Void> run(Runnable task) {
        return CompletableFuture.runAsync(task, executor);
    }

    @Override
    public void close() {
        executor.close();
    }
}
