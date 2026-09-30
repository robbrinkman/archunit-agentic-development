package com.jdriven.example.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{orderId}/complete")
    public OrderDto complete(@PathVariable String orderId) {
        return OrderDto.from(orderService.complete(orderId));
    }

    @GetMapping
    public List<OrderDto> findAll() {
        return orderService.findAll().stream().map(OrderDto::from).toList();
    }
}
