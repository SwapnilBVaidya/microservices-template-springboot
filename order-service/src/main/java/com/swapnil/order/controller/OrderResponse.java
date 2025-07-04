package com.swapnil.order.controller;

import java.time.LocalDateTime;

public class OrderResponse {
    private Long id;
    private Long userId;
    private String status;
    private LocalDateTime createdAt;
    private OrderItemResponse items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OrderItemResponse getItems() {
        return items;
    }

    public void setItems(OrderItemResponse items) {
        this.items = items;
    }
}
