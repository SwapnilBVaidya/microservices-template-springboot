package com.swapnil.order.controller;

import java.util.List;

public class OrderRequest {
    private List<OrderItemsRequest> items;

    public List<OrderItemsRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemsRequest> items) {
        this.items = items;
    }
}
class OrderItemsRequest {
    private Long productId;
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
